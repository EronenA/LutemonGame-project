package com.example.lutemongame;

public class Description {
    String description;
    int imageLutemon1;
    int imageLutemon2;
    int imageVisualDescription1;
    int imageVisualDescription2;

    public Description(String description, int imageLutemon1, int imageVisualDescription1, int imageVisualDescription2, int imageLutemon2)    {
        this.description = description;
        this.imageLutemon1 = imageLutemon1;
        this.imageLutemon2 = imageLutemon2;
        this.imageVisualDescription1 = imageVisualDescription1;
        this.imageVisualDescription2 = imageVisualDescription2;
    }

    public String getDescription() {
        return description;
    }

    public int getImageLutemon1() {
        return imageLutemon1;
    }

    public int getImageLutemon2() {
        return imageLutemon2;
    }

    public int getImageVisualDescription1() {
        return imageVisualDescription1;
    }

    public int getImageVisualDescription2() {
        return imageVisualDescription2;
    }

}
