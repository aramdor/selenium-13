package tests.user;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductPropertiesObject {
    String productName;
    String manufacturer;
    String regularPrice;
    String campaignPrice;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setCampaignPrice(String specialPrice) {
        this.campaignPrice = specialPrice;
    }

    public String getCampaignPrice() {
        return campaignPrice;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Name is: [" + getProductName() + "] and Manufacturer is: [" + getManufacturer() + "] and Regular price is: [" + getRegularPrice() + "] and Campaign price is: [" + getCampaignPrice() + "]";
    }
}
