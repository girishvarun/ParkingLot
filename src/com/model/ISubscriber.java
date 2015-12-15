package com.model;

/**
 * Created by girishv on 15/12/15.
 */
public interface ISubscriber {

    void subscribeToFullLotNotification();
    void getEmptyLotNotification();
    void getFullLotNotification();
}
