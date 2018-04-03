package crop;

// handling command line arguments
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class Main {
  @Parameter(names = {"--xPos", "-x"},
             description = "x-coordinate to begin cropping from")
    int xPos;
  
  @Parameter(names = {"--yPos", "-y"},
             description = "y-coordinate to begin cropping from")
    int yPos;

  @Parameter(names = {"--width", "-w"},
             description = "Width of the crop")
    int width;

  @Parameter(names = {"--height", "-h"},
             description = "Height of the crop")
    int height;

  @Parameter(names = {"--path", "-p"},
             description = "Filepath of images to crop")
    String filepath;

public static void main (String[] args) {
    Main main = new Main();
    JCommander.newBuilder()
      .addObject(main)
      .build()
      .parse(args);

    main.run();
  }

  /**
   * perform conversions
   */ 
  public void run() {
    Cropper crp = new Cropper(xPos, yPos, width, height);
    crp.convert(filepath);
  }
}
