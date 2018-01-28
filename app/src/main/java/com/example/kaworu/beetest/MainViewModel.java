package com.example.kaworu.beetest;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by kaworu on 28/1/18.
 */

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> oneOneInput;
    private MutableLiveData<String> oneTwoInput;
    private MutableLiveData<String> oneOutput;

    private MutableLiveData<String> twoInput;
    private MutableLiveData<String> twoOutput;

    public MainViewModel() {
        super();
        EventBus.getDefault().register(this);
    }

    public MutableLiveData<String> OneOneInput() {
        if (oneOneInput == null) {
            oneOneInput = new MutableLiveData<>();
            oneOneInput.setValue("0");
        }
        return oneOneInput;
    }

    public MutableLiveData<String> OneTwoInput() {
        if (oneTwoInput == null) {
            oneTwoInput = new MutableLiveData<>();
            oneTwoInput.setValue("0");
        }
        return oneTwoInput;
    }

    public MutableLiveData<String> OneOutput() {
        if (oneOutput == null) {
            oneOutput = new MutableLiveData<>();
            oneOutput.setValue("0");
        }
        return oneOutput;
    }

    public MutableLiveData<String> TwoInput() {
        if (twoInput == null) {
            twoInput = new MutableLiveData<>();
            twoInput.setValue("0");
        }
        return twoInput;
    }

    public MutableLiveData<String> TwoOutput() {
        if (twoOutput == null) {
            twoOutput = new MutableLiveData<>();
            twoOutput.setValue("0");
        }
        return twoOutput;
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onGotFirstSum(GotFirstSumEvent event) {
        OneOutput().setValue(event.sum);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onGotSecondSum(GotSecondSumEvent event) {
        TwoOutput().setValue(event.sum);
    }

    public void calcOne() {
        ArrayList<Byte> first = new ArrayList<>();
        ArrayList<Byte> second = new ArrayList<>();
        if (OneOneInput().getValue().length() > 0) {
            for (int i = 0; i < OneOneInput().getValue().length(); i++) {
                first.add(Byte.decode(Character.toString(OneOneInput().getValue().charAt(i))));
            }
        }
        else first.add((byte)0);
        if (OneTwoInput().getValue().length() > 0) {
            for (int i = 0; i < OneTwoInput().getValue().length(); i++) {
                second.add(Byte.decode(Character.toString(OneTwoInput().getValue().charAt(i))));
            }
        }
        else second.add((byte) 0);
        Calc.getInstance().calcFirst(first, second);
    }

    public void calcTwo() {
        Calc.getInstance().calcSecond(Integer.parseInt(TwoInput().getValue()));
    }
}
