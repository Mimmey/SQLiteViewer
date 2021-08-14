package viewer;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class ApplicationRunner {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Runnable sqliteViewer = SQLiteViewer::new;
        SwingUtilities.invokeAndWait(sqliteViewer);
    }
}
