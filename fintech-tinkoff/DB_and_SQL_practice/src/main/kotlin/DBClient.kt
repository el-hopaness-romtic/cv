import java.sql.DriverManager.getConnection
import java.sql.PreparedStatement


class DBClient(url: String) {
    private val connection = getConnection(url)

    fun close() {
        connection.close()
    }

    fun getPreparedStatement(sql: String, vararg parameters: Any): PreparedStatement =
        connection.prepareStatement(sql).apply {
            parameters.forEachIndexed { index, parameter ->
                when (parameter) {
                    is Int -> setInt(index + 1, parameter)
                    is String -> setString(index + 1, parameter)
                    else -> throw IllegalArgumentException("Only Int and String are supported yet")
                }
            }
        }
}
