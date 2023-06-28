package tacos.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import tacos.data.OrderRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderCountInfoContributor implements InfoContributor {
    private final OrderRepository tacoRepo;

    @Override
    public void contribute(Builder builder) {
        long tacoCount = tacoRepo.count();
        Map<String, Object> tacoMap = new HashMap<>();
        tacoMap.put("count", tacoCount);
        builder.withDetail("order-stats", tacoMap);
    }
}
