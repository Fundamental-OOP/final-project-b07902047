package FlappyBird.view;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("Image not found");
    }
}
