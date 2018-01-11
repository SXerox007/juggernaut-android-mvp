package ui.onboarding.mvp;

import com.skeleton.mvp.ui.onboarding.signup.SignUpInteractorImpl;
import com.skeleton.mvp.ui.onboarding.signup.SignUpPresenterImpl;
import com.skeleton.mvp.ui.onboarding.signup.SignUpView;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

}
