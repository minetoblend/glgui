package example;

import com.minetoblend.gui.Window;
import org.joml.Vector2i;

public class SampleApp {

    public static void main(String[] args) throws InterruptedException {

        var window = new Window();

        window.setVisible(true);

        Thread.sleep(5000);
        window.setSize(new Vector2i(200,200));

    }

}
