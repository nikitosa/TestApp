package com.example.nikitos.testapp;

import android.util.Log;

public class Logic {

    private Brick brickClass;

    private int height;
    private int width;

    private int brickHeight;
    private int brickWidth;
    private int brickNumb;

    private boolean flag=true;

    private boolean build=true;
    private int empty=0;

    public boolean isBuild() {
        return build;
    }


    public Logic(Brick brick) {
        this.brickClass = brick;
        brickHeight=brickClass.getBrickHeight();
        brickWidth=brickClass.getBrickWidth();
        brickNumb=brickClass.getBrickNumb();
    }

    public void start(){
        height=FieldClass.height;
        width=FieldClass.width;
        setBricks();
    }

    private boolean checkEmpty(int q, int w){
        return FieldClass.field[q][w] == 0;
    }

    public void setBricks(){
        for(int y=0; y<width; y++){
            for (int x=0; x<height;x++){
                flag=true;
                if (flag) { //flag doesn't allow a program to find a place(horizontal), if there was a place (vertical)
                    empty=0;
                    try{
                        for(int y2=y; y2<(brickWidth+y); y2++){ //finding place for a brick (vertical)
                            for (int x2=x; x2 <(brickHeight+x); x2++) {
                                if (checkEmpty(x2, y2)) {
                                    empty++;
                                }
                            }
                        }
                        if(empty==brickHeight*brickWidth){//placing brick on the field (vertical)
                            for(int y2=y; y2<(brickWidth+y); y2++){
                                for (int x2=x; x2 <(brickHeight+x); x2++) {
                                    FieldClass.field[x2][y2]=Brick.brickType;
                                }
                            }
                            brickNumb--;
                            flag=false;
                        }

                    }
                    catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
                if (flag) {
                    empty=0;
                    try{
                        for(int y2=y; y2<(brickHeight+y); y2++){//finding place for a brick (horizontal)
                            for (int x2=x; x2 <(brickWidth+x); x2++) {
                                if (checkEmpty(x2, y2)) {
                                    empty++;
                                }
                            }
                        }
                        if(empty==brickHeight*brickWidth){//placing brick on the field (horizontal)
                            for(int y2=y; y2<(brickHeight+y); y2++){
                                for (int x2=x; x2 <(brickWidth+x); x2++) {
                                    FieldClass.field[x2][y2]=Brick.brickType;
                                }
                            }
                            brickNumb--;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
                if(brickNumb<1){break;}
            }
            if(brickNumb<1){break;}
        }

        //if bricks might set on the field(field square>bricks square), but they don't due to wrong placement
        //this block at first finds a place (horizontal), and after - (vertical)
        if(brickNumb>0&&((width*height)>=(brickClass.getSquare()*brickClass.getBrickNumb()))){
            brickNumb=brickClass.getBrickNumb();
            for(int y=0; y<width; y++){
                for (int x=0; x<height;x++){
                    if (FieldClass.field[x][y]==Brick.brickType) {
                        FieldClass.field[x][y]=0; //setting 0 to wrong placed bricks
                    }
                }
            }
            for(int y=0; y<width; y++){
                for (int x=0; x<height;x++){
                    flag=true;
                    if (flag) {
                        empty=0;
                        try{
                            for(int y2=y; y2<(brickHeight+y); y2++){
                                for (int x2=x; x2 <(brickWidth+x); x2++) {
                                    if (checkEmpty(x2, y2)) {
                                        empty++;
                                    }
                                }
                            }
                            if(empty==brickHeight*brickWidth){
                                for(int y2=y; y2<(brickHeight+y); y2++){
                                    for (int x2=x; x2 <(brickWidth+x); x2++) {
                                        FieldClass.field[x2][y2]=Brick.brickType;
                                    }
                                }
                                brickNumb--;
                                flag=false;
                            }
                        }
                        catch(IndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                    }
                    if (flag) {
                        empty=0;
                        try{
                            for(int y2=y; y2<(brickWidth+y); y2++){
                                for (int x2=x; x2 <(brickHeight+x); x2++) {
                                    if (checkEmpty(x2, y2)) {
                                        empty++;
                                    }
                                }
                            }
                            if(empty==brickHeight*brickWidth){
                                for(int y2=y; y2<(brickWidth+y); y2++){
                                    for (int x2=x; x2 <(brickHeight+x); x2++) {
                                        FieldClass.field[x2][y2]=Brick.brickType;
                                    }
                                }
                                brickNumb--;
                            }

                        }
                        catch(IndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                    }
                    if(brickNumb<1){break;}
                }
                if(brickNumb<1){break;}
            }
        }
        if(brickNumb>0){
            build=false;
        }
    }


}
