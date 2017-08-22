package icatalog.pkp.faisal.icatalog.Model;

/**
 * Created by faisal.ariyanto on 11/2/2016.
 */
public class ItemModel {
    public String Id;
//    public String SellerId;
    public String CategoryId;
    public String Name;
    public int[] Feedback;
    public int Price;
    public String PhotoUrl;

    public ItemModel(String Id,
//                          String SellerId,
                          String CategoryId,
                          String Name,
                          int[] Feedback,
                          int Price,
                          String PhotoUrl){
        this.Id = Id;
//        this.SellerId = SellerId;
        this.CategoryId = CategoryId;
        this.Name = Name;
        this.Feedback = Feedback;
        this.Price = Price;
        this.PhotoUrl = PhotoUrl;
    }

    public ItemModel getItem(){

        return new ItemModel(
                this.Id,
//                this.SellerId,
                this.CategoryId,
                this.Name,
                this.Feedback,
                this.Price,
                this.PhotoUrl
        );

    }


}
