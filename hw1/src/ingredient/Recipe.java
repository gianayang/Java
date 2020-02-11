
/**
 *
 * @author giana
 */
import java.util.*;

class Ingredient {
    public String name;
    public double measurement;
    public void setData(double num, String na){
        measurement = num;
        name = na;
    }

}

public class Recipe {
    private String[] recipe = new String[5];
    private Ingredient[] ingredient = new Ingredient[4];
    
    @Override
    public String toString() {
        String res = "";
        for (Ingredient i : ingredient) {
            res += Double.toString(i.measurement) + '/'+ i.name + '\n';
        }
        res += "\r\n\r\n";
        for (String i: recipe) {
            res += i + "\r\n";
        }
        return res;
    }

    public static void main(String[]args) {
        Recipe cookie = new Recipe();
        Ingredient saltedButter = new Ingredient();
        saltedButter.setData(1.0, "cup salted butter");
        cookie.ingredient[0] = saltedButter;
        Ingredient sugar = new Ingredient();
        sugar.setData(1.0, "cup light sugar");
        cookie.ingredient[1] = sugar;
        Ingredient extract = new Ingredient();
        extract.setData(2.0,"tsp pure vanilla extract");
        cookie.ingredient[2] = extract;
        Ingredient bakingsoda = new Ingredient();
        bakingsoda.setData(1.0,"tsp baking soda");
        cookie.ingredient[3] = bakingsoda;

        cookie.recipe[0] = "1. Preheat oven to 375 degrees F. Line a baking pan with parchment paper and set aside.";
        cookie.recipe[1] = "2. In a separate bowl mix flour, baking soda, salt, baking powder. Set aside.";
        cookie.recipe[2] = "3. Cream together butter and sugars until combined.";
        cookie.recipe[3] = "4. Beat in eggs and vanilla until fluffy.";
        cookie.recipe[4] = "5. Mix in the dry ingredients until combined.";

        System.out.println(cookie);
    }
}


