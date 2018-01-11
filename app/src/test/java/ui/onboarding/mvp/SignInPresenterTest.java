package ui.onboarding.mvp;

import com.skeleton.mvp.ui.onboarding.signin.SignInInteractorImpl;
import com.skeleton.mvp.ui.onboarding.signin.SignInPresenterImpl;
import com.skeleton.mvp.ui.onboarding.signin.SignInView;

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
public class SignInPresenterTest {
    @Mock
    private SignInInteractorImpl interactor;
    @Mock
    private SignInView view;
    @Mock
    private SignInPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SignInPresenterImpl(view, interactor);
        presenter.onAttach();
    }

    @Test
    public void checkIfViewIsAttachedOnAttach() {
        assertEquals(true, presenter.isViewAttached());
    }

    @Test
    public void checkIfShowsEmailErrorOnSignInClicked() {
        presenter.onSignInClicked("test@test", "12345678");
        verify(view, times(1)).showErrorMessage(Mockito.anyInt());
    }

    @Test
    public void checkIfShowsPasswordErrorOnSignInClicked() {
        presenter.onSignInClicked("test@test.com", "123");
        verify(view, times(1)).showErrorMessage(Mockito.anyInt());
    }

    @Test
    public void checkIfShowsProgressOnSignInClicked() {
        presenter.onSignInClicked("test@test.com", "Qwerty12");
        verify(view, times(1)).showLoading();
    }

    @Test
    public void checkIfViewIsDetachedOnDetach() {
        presenter.onDetach();
        assertEquals(false, presenter.isViewAttached());
    }
}
