package com.example.nikitos.testapp;

import android.util.Log;

import com.example.nikitos.testapp.Bricks.LargeBrickClass;
import com.example.nikitos.testapp.Bricks.MediumBrickClass;
import com.example.nikitos.testapp.Bricks.SmallBrickClass;

public class LogicClass {

    private FieldClass fieldClass;
    private SmallBrickClass smallBrickClass;
    private MediumBrickClass mediumBrickClass;
    private LargeBrickClass largeBrickClass;

    private int height;
    private int width;
    private int numbSmall;
    private int numbMedium;
    private int numbLarge;

    private boolean build=true;

    public boolean isBuild() {
        return build;
    }

    private int [] [] field; //field is represented as two-dimensional array

    public LogicClass(FieldClass fieldClass, SmallBrickClass smallBrickClass, MediumBrickClass mediumBrickClass, LargeBrickClass largeBrickClass) {
        this.fieldClass = fieldClass;
        this.smallBrickClass = smallBrickClass;
        this.mediumBrickClass = mediumBrickClass;
        this.largeBrickClass = largeBrickClass;
    }

    public void startLogic(){
        height=fieldClass.getHeight();
        width=fieldClass.getWidth();
        numbSmall=smallBrickClass.getBricksNumb();
        numbMedium=mediumBrickClass.getBricksNumb();
        numbLarge= largeBrickClass.getBricksNumb();

        field = new int [height][width];

        if((height*width)<(numbSmall+numbMedium*2+numbLarge*3)){ //checking is there enough space for all bricks
            build=false;
        }
        else{
            setBigBricks(); // placing bricks on a field
            setMediumBricks();
            setSmallBricks();
            FieldClass f=new FieldClass();
            f.setField(field);

            if((numbSmall+numbMedium+numbLarge)>0){ //checking for extra bricks
             build=false;
            }
        }
    }

    private boolean checkEmpty(int q, int w){
        if (field[q][w]==0) {
            return true;
        }
        else{
            return false;
        }
    }

    private void setBigBricks(){
        if(numbLarge>0){
            for(int y=0; y<width; y++){
                for (int x=0; x<height;x++){
                    try{
                        if(checkEmpty(x,y)&&checkEmpty(x+1,y) && checkEmpty(x+2,y)){ //checking cells on the right
                            field[x][y] = 3; // for large bricks - value in the cell will be 3
                            field[x+1][y] = 3;
                            field[x+2][y] = 3;
                            numbLarge--;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    try{
                        if(checkEmpty(x,y)&&checkEmpty(x,y+1) && checkEmpty(x,y+2)){ // checking down cells
                            field[x][y] = 3;
                            field[x][y+1] = 3;
                            field[x][y+2] = 3;
                            numbLarge--;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    if(numbLarge<1){break;}
                }
                if(numbLarge<1){break;}
            }
        }
    }

    private void setMediumBricks(){
        if(numbMedium>0) {
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < height; x++) {
                    try {
                        if (checkEmpty(x, y) && checkEmpty(x + 1, y)) { //checking cells on the right
                            field[x][y] = 2; // for medium bricks - value in the cell will be 2
                            field[x + 1][y] = 2;
                            numbMedium --;
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (checkEmpty(x, y) && checkEmpty(x, y + 1)) { // checking down cells
                            field[x][y] = 2;
                            field[x][y + 1] = 2;
                            numbMedium --;
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    if(numbMedium<1){break;}
                }
                if(numbMedium<1){break;}
            }
        }
    }
    private void setSmallBricks(){
        if(numbSmall>0){
            for(int y=0; y<width; y++){
                for (int x=0; x<height;x++){
                    try{
                        if(checkEmpty(x,y)){
                            field[x][y] = 1; // for small bricks - value in the cell will be 1
                            numbSmall--;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    if(numbSmall<1){break;}
                }
                if(numbSmall<1){break;}
            }
        }
    }
}
