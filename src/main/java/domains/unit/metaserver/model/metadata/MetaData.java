package domains.unit.metaserver.model.metadata;

import java.util.List;

/**
 *
 * Json 格式如下：
 */
public class MetaData {
    String name;
    String description;
    List<MetaDataAttribute> attributes;
    int name_length;
    String image;
    String image_url;
    String image_data;
    String url;
    String external_url;
    String background_color;
    String animation_url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MetaDataAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<MetaDataAttribute> attributes) {
        this.attributes = attributes;
    }

    public int getName_length() {
        return name_length;
    }

    public void setName_length(int name_length) {
        this.name_length = name_length;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_data() {
        return image_data;
    }

    public void setImage_data(String image_data) {
        this.image_data = image_data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getAnimation_url() {
        return animation_url;
    }

    public void setAnimation_url(String animation_url) {
        this.animation_url = animation_url;
    }
}
