package id.ac.awesomeapp.model;

public class ImageModel {
    private String photographer;
    private String photographer_url;
    private Integer Id;
    private String urlImage;

    public  ImageModel(){

    }

    public ImageModel(String photographer, String photographer_url, Integer id, String urlImage) {
        this.photographer = photographer;
        this.photographer_url = photographer_url;
        this.Id = id;
        this.urlImage = urlImage;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotographer_url() {
        return photographer_url;
    }

    public void setPhotographer_url(String photographer_url) {
        this.photographer_url = photographer_url;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
