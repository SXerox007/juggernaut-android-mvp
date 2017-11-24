package com.skeleton.mvpbase.ui.base;

/**
 * Created by cl-macmini-01 on 9/20/17.
 */

public interface BasePresenter {

    /**
     * Indicates when the view has attached ( created )
     */
    void onAttach();

    /**
     * Indicates when the view has detached ( destroyed )
     */
    void onDetach();

    /**
     * Parses the message id from the throwable
     *
     * @param throwable the cause of network failure
     * @return the parsed message id
     */
    int parseThrowableMessage(Throwable throwable);
}
