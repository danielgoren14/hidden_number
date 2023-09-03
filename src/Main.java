import utils.ApiUtil;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)  {

        SwingUtilities.invokeLater(() -> {
            new BufferedImageResizeExample();
        });
//        ApiUtil.submit("95628161",95);
    }
}
