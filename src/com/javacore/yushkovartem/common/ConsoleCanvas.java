package com.javacore.yushkovartem.common;

public class ConsoleCanvas extends Canvas {

    private char[][] pixes;

    private int width;
    private int height;

    public ConsoleCanvas(int width, int height){
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        pixes = new char[height][width];
        reset();
    }

    private void reset() {
        for (int i=0; i < height; i++){
            for (int j = 0; j < width; j++) {
                pixes[i][j] = '.';
            }
        }
    }

    public void draw() {
        for (int i=0; i < height; i++){
            System.out.print("\n");
            for (int j = 0; j < width; j++) {
                System.out.print(pixes[i][j] + " ");
            }
        }
    }

    public void setSymbolAt(int x, int y, char symbol){
        pixes[y][x] = symbol;
    }

    @Override
    public void drawTextAt(String text, int x, int y, int endX, int endY) {
        char[] cText = text.toCharArray();
        int linePointer = x;
        int rowPointer = y;
        for (char c : cText) {
            setSymbolAt(linePointer, rowPointer, c);
            linePointer++;
            if (linePointer == endX) {
                linePointer = x;
                rowPointer++;
            }
            if (rowPointer > endY) {
                System.out.println("Ошибка! Текст слишком большой");
                break;
            }
        }
        draw();
    }



    @Override
    public void drawSquare(int size) {

    }

    public void drawSquareAt(int x, int y, int size) {
        if (x + size > width || y + size > height){
            System.out.println("Error! Make your square smaller, or change coordinates!");
        } else{
            for (int i = 0; i < size; i++) {
                pixes[y][x + i] = '#';
            }
            for (int i = 1; i < size -1; i++) {
                pixes[y + i][x] = '#';
                pixes[y + i][x + size-1] = '#';
            }
            for (int i = 0; i < size ; i++) {
                pixes[y + size-1][x + i] = '#';
            }
            draw();
        }
    }

    public void drawCircleAt(int x, int y, int r){
        if (x + r > width || x - r < 0 || y + r > height || y - r < 0){
            System.out.println("Error! Make your circle smaller, or change coordinates!");
        } else{
            int x1 = 0;
            int y1 = r;
            float dp = 3 - 2*r;
            while(x1 <= y1){
                if (dp <= 0){
                    dp += (4*x1) + 6;
                } else {
                    dp += 4*(x1 - y1) + 10;
                    y1--;
                }

                x1++;

                setSymbolAt(x1+x,y1+y,'#');
                setSymbolAt(x1+x,y-y1,'#');
                setSymbolAt(x-x1,y1+y,'#');
                setSymbolAt(x-x1,y-y1,'#');
                setSymbolAt(x+y1,y+x1,'#');
                setSymbolAt(x+y1,y-x1,'#');
                setSymbolAt(x-y1,y+x1,'#');
                setSymbolAt(x-y1,y-x1,'#');

            }

            draw();
        }
    }
}
