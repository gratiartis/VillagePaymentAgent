package org.haftrust.verifier.controller;

import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.view.PreRegisterVerifierBean;
import org.junit.Test;
import org.springframework.validation.BindingResult;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PreRegisterVerifierControllerTest {

    private final VerifierService verifier = mock(VerifierService.class);
    private final PreRegisterVerifierController subject = new PreRegisterVerifierController(verifier);

    @Test
    public void onSubmitShouldRedirectToBackToFormIfValidationFailure() throws Exception {
        final PreRegisterVerifierBean command = new PreRegisterVerifierBean();
        final BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals(PreRegisterVerifierController.FORM_JSP_PAGE, subject.onSubmit(command, bindingResult));
    }

    @Test
    public void onSubmitShouldForwardToConfirmationPageWhenNoValidationProblems() throws Exception {
        final String email = randomAlphanumeric(20);
        final String password = randomAlphanumeric(20);
        final int idVerifier = new Random().nextInt();

        final PreRegisterVerifierBean command = new PreRegisterVerifierBean();
        command.setEmail(email);
        command.setPassword(password);

        final BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(false);

        when(verifier.preRegisterVerifier(email, password)).thenReturn(idVerifier);

        assertEquals(PreRegisterVerifierController.RESULT_JSP_PAGE, subject.onSubmit(command, bindingResult));

        assertEquals(idVerifier, command.getIdVerifier());
    }
}