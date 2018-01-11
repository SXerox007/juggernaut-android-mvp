package ui.onboarding.mvp;

import com.skeleton.mvp.ui.onboarding.signup.SignUpInteractorImpl;
import com.skeleton.mvp.ui.onboarding.signup.SignUpPresenterImpl;
import com.skeleton.mvp.ui.onboarding.signup.SignUpView;
import com.skeleton.mvp.util.ValidationUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sumitthakur on 11/01/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterTest {
    @Mock
    private SignUpInteractorImpl interactor;
    @Mock
    private SignUpView view;
    @Mock
    private SignUpPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SignUpPresenterImpl(view, interactor);
        presenter.onAttach();
    }

    @Test
    public void checkIfViewIsAttachedOnAttach() {
        assertEquals(true, presenter.isViewAttached());
    }

    @Test
    public void checkOnSignUpClicked() {
        presenter.onSignUpClicked("sumit@gmail.com", "sumit","12345678","12345678");
        verify(view, times(1)).showErrorMessage(Mockito.anyInt());
    }

//    @Test
//    public void check_password_match_or_not(){
//        Assert.assertEquals(false, );
//    }


    @Test
    public void checkIfViewIsDetachedOnDetach() {
        presenter.onDetach();
        assertEquals(false, presenter.isViewAttached());
    }





}
