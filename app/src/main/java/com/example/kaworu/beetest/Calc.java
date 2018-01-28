package com.example.kaworu.beetest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by kaworu on 28/1/18.
 */

public class Calc {

    private static Calc instance;

    public static Calc getInstance() {
        if (instance == null) instance = new Calc();
        return instance;
    }

    public void calcFirst(ArrayList<Byte> first, ArrayList<Byte> second) {
        String sum = "";
        ArrayList<Byte> result = new ArrayList<>();
        Byte tmp;
        int sizeOne = first.size()-1;
        int sizeTwo = second.size()-1;
        int index;
        if (sizeOne > sizeTwo) index = sizeTwo;
        else index = sizeOne;
        for (int i = 0; i <= index; i++) {
            tmp = (byte)(first.get(sizeOne-i)+second.get(sizeTwo-i));
            if (tmp > 9) {
                tmp = (byte) (tmp-10);
                if (sizeOne > sizeTwo) {
                    first.set(sizeOne-i-1, (byte)(first.get(sizeOne-i-1)+1));
                    result.add(tmp);
                }
                else if (sizeTwo > sizeOne) {
                    second.set(sizeTwo-i-1, (byte)(second.get(sizeTwo-i-1)+1));
                    result.add(tmp);
                }
                else if (sizeOne == sizeTwo) {
                    if (sizeOne-i-1 >= 0) {
                        first.set(sizeOne-i-1, (byte)(first.get(sizeOne-i-1)+1));
                        result.add(tmp);
                    }
                    else {
                        result.add(tmp);
                        result.add((byte)1);
                    }
                }
            }
            else result.add(tmp);
        }
        if (sizeOne > sizeTwo) {
            for (int i = sizeOne - sizeTwo - 1; i > 0; i--) {
                tmp = first.get(i);
                if (first.get(i) > 9) {
                    tmp = (byte) (first.get(i) - 10);
                    first.set(i-1, (byte)(first.get(i-1)+1));
                }
                result.add(tmp);
            }
            tmp = first.get(0);
            if (first.get(0) > 9) {
                tmp = (byte) (first.get(0)-10);
                result.add(tmp);
                result.add((byte)1);
            }
            else result.add(tmp);
        }
        else if (sizeTwo > sizeOne) {
            for (int i = sizeTwo - sizeOne - 1; i > 0; i--) {
                tmp = second.get(i);
                if (second.get(i) > 9) {
                    tmp = (byte) (second.get(i) - 10);
                    second.set(i-1, (byte)(second.get(i-1)+1));
                }
                result.add(tmp);
            }
            tmp = second.get(0);
            if (second.get(0) > 9) {
                tmp = (byte) (second.get(0)-10);
                result.add(tmp);
                result.add((byte)1);
            }
            else result.add(tmp);
        }
        for (int i = result.size()-1; i >= 0; i--) {
            sum += String.valueOf((int)result.get(i));
        }
        EventBus.getDefault().post(new GotFirstSumEvent(sum));
    }

    public void calcSecond(int input) {
        calcSecond(input, 0);
    }

    public void calcSecond(int input, int sum) {
        if (input == 0) {
            EventBus.getDefault().post(new GotSecondSumEvent(String.valueOf(sum)));
        }
        else {
            sum += input % 10;
            calcSecond(input/10, sum);
        }
    }
}
