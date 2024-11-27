import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void testGetDefault() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "taskManager должен быть проинициализирован");
    }

    @Test
    void testGetDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "historyManager должен быть проинициализирован");
    }
}