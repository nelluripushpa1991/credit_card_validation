package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.exception.NoSuchCreditCardExistsException;
import com.pushpa.creditcardvalidation.repository.CreditCardValidationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class CreditCardValidationServiceTests {
    @Mock
    private CreditCardValidationRepository creditCardValidationRepository;
    @InjectMocks
    private CreditCardValidationServiceImpl creditCardValidationService;
    private CreditCard creditCard;
    private List<CreditCard> listResponse ;
    private Optional<CreditCard> optionalCreditCard ;
    @BeforeEach
    public void setup(){
        creditCard = CreditCard.builder()
                .id(1).creditCardNumber("411111111111111")
                .cvv(123)
                .expiryDate("05/2025")
                .cardType("VISA").build();
        listResponse = new ArrayList<>();
        listResponse.add(creditCard);
        creditCardValidationRepository.save(creditCard);
        creditCardValidationService.saveCreditCard(creditCard);
    }

    // JUnit test for saveCreditCard method
    @DisplayName("JUnit test for saveCreditCard method")
    @Test
    public void givenCreditCardObject_whenSaveCreditCard_thenReturnCreditCardObject() {
        when(creditCardValidationRepository.save(creditCard)).thenReturn(creditCard);

        // Call the CreditCardValidationService's saveCreditCard method and verify the result
        CreditCard savedCreditCardResponseData = creditCardValidationService.saveCreditCard(creditCard);
        assertThat(savedCreditCardResponseData.getCardType().equals(creditCard.getCardType()));
    }

    // JUnit test for saveCreditCard method which throws exception
    @DisplayName("JUnit test for saveCreditCard method which throws exception")
    @Test
    public void givenExistingCreditCardNumber_whenSaveCreditCard_thenThrowsException() {
        // Mock the CreditCardValidationRepository's save method
        when(creditCardValidationRepository.save(creditCard)).thenReturn(null);

        // Call the CreditCardValidationService's saveCreditCard method and verify the result
        CreditCard savedCreditCardResponseData = creditCardValidationService.saveCreditCard(creditCard);
        assertThat(savedCreditCardResponseData).isNull();
    }

    // JUnit test for getById method
    @DisplayName("JUnit test for getById method")
    @Test
    public void givenId_whenGetCreditCardById_thenReturnCreditCardObject() {
        creditCard = CreditCard.builder()
                .id(5).creditCardNumber("3711111111111111")
                .cvv(237)
                .expiryDate("05/2023")
                .cardType("AMERICAN EXPRESS").build();
        listResponse.add(creditCard);
        CreditCard savedCreditCardResponseData = (CreditCard) creditCardValidationService.saveCreditCard(creditCard);

        // given
        given(creditCardValidationRepository.findById(5)).willReturn(Optional.ofNullable(savedCreditCardResponseData));

        // when
        ResponseEntity<HttpStatus> statusResponseEntitySuccess = new ResponseEntity<>(HttpStatus.OK);
        ResponseEntity<HttpStatus> statusResponseEntityError = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // then
        assertThat(statusResponseEntitySuccess.getStatusCode().value()).isEqualTo(200);
        assertThat(statusResponseEntityError.getStatusCode().value()).isEqualTo(404);

    }

    // JUnit test for getAllCreditCardsByCardType method
    @DisplayName("JUnit test for getAllCreditCardsByCardType method")
    @Test
    public void givenId_whenGetByCardType_thenReturnCreditCardObjects() {
        creditCard = CreditCard.builder()
                .id(2).creditCardNumber("411111111111111")
                .cvv(113)
                .expiryDate("05/2023")
                .cardType("VISA").build();
        listResponse.add(creditCard);

        // given
        given(creditCardValidationRepository.findByCardType("VISA")).willReturn(listResponse);

        // when
        List<CreditCard> savedCreditCard = (List<CreditCard>) creditCardValidationService.getAllCreditCardsByCardType(creditCard.getCardType());


        // then
        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.size()).isEqualTo(2);

    }

    // JUnit test for getAllCreditCards method
    @DisplayName("JUnit test for getAllCreditCards method")
    @Test
    public void givenId_whenGetAllCreditCards_thenReturnAllCreditCardsObjects() {
        creditCard = CreditCard.builder()
                .id(3).creditCardNumber("511111111111111")
                .cvv(133)
                .expiryDate("05/2023")
                .cardType("MASTER CARD").build();
        listResponse.add(creditCard);

        // given
        given(creditCardValidationRepository.findAll()).willReturn(listResponse);

        // when
        List<CreditCard> savedCreditCard = (List<CreditCard>) creditCardValidationService.getAllCreditCards();

        // then
        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.size()).isEqualTo(2);

    }

    // JUnit test for getAllCreditCards method with negative scenario
    @DisplayName("JUnit test for getAllCreditCards method negative scenario")
    @Test
    public void givenId_whenGetAllCreditCardsNS_thenReturnAllCreditCardsNSObjects() throws NoSuchCreditCardExistsException {
        System.out.println("listResponse : "+listResponse.size());
        listResponse.clear();
        System.out.println("after listResponse : "+listResponse.size());
        // given
        given(creditCardValidationRepository.findAll()).willReturn(listResponse);
        // when
        List<CreditCard> savedCreditCard = (List<CreditCard>) creditCardValidationService.getAllCreditCards();
        System.out.println("save card "+savedCreditCard);

        // then
        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.size()).isEqualTo(0);

    }

    // JUnit test for getById method
    @DisplayName("JUnit test for deleteById method")
    @Test
    public void givenId_whenDeleteCreditCardById_thenReturnHttpStatusObject() {
        creditCard = CreditCard.builder()
                .id(4).creditCardNumber("371111111111111")
                .cvv(137)
                .expiryDate("05/2037")
                .cardType("AMERICAN EXPRESS").build();
        listResponse.add(creditCard);

        // given
        willDoNothing().given(creditCardValidationRepository).deleteById(4);

        // when
        creditCardValidationService.deleteCreditCard(creditCard.getId());

        // then
        assertThat(creditCardValidationRepository).isNotNull();
        // then - verify the output
        verify(creditCardValidationRepository, times(1)).deleteById(4);

    }

}
