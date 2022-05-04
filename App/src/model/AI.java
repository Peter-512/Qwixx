package App.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AI {
    private  LinkedHashMap<Color, Row> rows;
    private  NumberField index;
    private final ArrayList<NumberField> numberFields = new ArrayList<>();

    public NumberField getIndex() {return index;}
    public Row getRow(Color color) {return rows.get(color);}

    public ArrayList<NumberField> getNumberFields() {
        return numberFields;
    }

    public HashMap<Color,NumberField> getPublicNumberFields(int total){
        HashMap<Color, NumberField> map = new HashMap<>();

        for (Color color : Color.values()) {
            Row row = getRow(color);

            for(int index = 0; index<=2; index++){


            }


        }



        return map;
    }

}


