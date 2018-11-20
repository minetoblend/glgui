package example;

import com.minetoblend.gui.Window;
import com.minetoblend.gui.element.BasicElement;
import com.minetoblend.gui.element.Scene;
import org.joml.Vector2i;

public class SampleApp {

    public static void main(String[] args) throws InterruptedException {

        var window = new Window();

        var rootPane = new BasicElement();
        var scene = new Scene(rootPane);

        window.setScene(scene);

        window.setVisible(true);


    }

}
