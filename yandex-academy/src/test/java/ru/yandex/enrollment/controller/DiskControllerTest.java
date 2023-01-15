package ru.yandex.enrollment.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.yandex.enrollment.annotation.SpringPostgresTest;
import ru.yandex.enrollment.repository.SystemItemRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureWebMvc
@SpringPostgresTest
@SpringJUnitWebConfig
@EnableWebMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiskControllerTest {
    @Autowired
    private DiskController diskController;
    @Autowired
    private SystemItemRepository repository;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(diskController)
                .setControllerAdvice(new ExceptionHandlerAdvice())
                .alwaysDo(print())
                .build();
    }

    @Test
    void deleteElement() throws Exception {
        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "element_to_delete",
                                      "url": "/file/url1",
                                      "parentId": null,
                                      "size": 234,
                                      "type": "FILE"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:01.000Z"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/delete/element_to_delete"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        assertThat(repository.findByItemId("element_to_delete")).isNotPresent();
    }

    @Test
    void findElement() throws Exception {
        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "element_to_find2",
                                      "url": "/file/url1",
                                      "parentId": null,
                                      "size": 234,
                                      "type": "FILE"
                                    },
                                    {
                                      "id": "element_to_find",
                                      "url": "/file/url1",
                                      "parentId": "folder_to_not_find",
                                      "size": 234,
                                      "type": "FILE"
                                    },
                                    {
                                      "id": "folder_to_not_find",
                                      "url": null,
                                      "parentId": null,
                                      "size": null,
                                      "type": "FOLDER"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:01.000Z"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(get("/updates?date=2022-05-29T03:28:02.000Z")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "element_to_find",
                            "url": "/file/url1",
                            "parentId": "folder_to_not_find",
                            "size": 234,
                            "type": "FILE"
                        }, {
                            "id": "element_to_find2",
                            "url": "/file/url1",
                            "parentId": null,
                            "size": 234,
                            "type": "FILE"
                        }]
                        """));
    }

    @Test
    void deleteFolderAndElement() throws Exception {
        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "element_to_delete_",
                                      "url": "/file/url1",
                                      "parentId": "folder_to_delete",
                                      "size": 234,
                                      "type": "FILE"
                                    },
                                    {
                                      "id": "folder_to_delete",
                                      "url": null,
                                      "parentId": null,
                                      "size": null,
                                      "type": "FOLDER"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:01.000Z"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/delete/folder_to_delete"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        assertThat(repository.findByItemId("folder_to_delete")).isNotPresent();
        assertThat(repository.findByItemId("element_to_delete_")).isNotPresent();
    }

    @Test
    void imports() throws Exception {
        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "элемент_1_4",
                                      "url": "/file/url1",
                                      "parentId": "элемент_1_1_2",
                                      "size": 234,
                                      "type": "FILE"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:01.000Z"
                                }
                                """))
                .andExpect(status().is(400));
    }

    @Test
    void imports2() throws Exception {
        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "element_1_1",
                                      "url": null,
                                      "parentId": null,
                                      "size": null,
                                      "type": "FOLDER"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:00.000Z"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        mockMvc.perform(post("/imports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "items": [
                                    {
                                      "id": "element_1_4",
                                      "url": "/file/url1",
                                      "parentId": "element_1_1",
                                      "size": 234,
                                      "type": "FILE"
                                    }
                                  ],
                                  "updateDate": "2022-05-28T21:12:01.000Z"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        mockMvc.perform(get("/nodes/element_1_1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
    }
}
