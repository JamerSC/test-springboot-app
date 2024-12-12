package com.springboot.fxn.testing;

import com.springboot.fxn.testing.dto.ClientAccountDto;
import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.mapper.AccountMapper;
import com.springboot.fxn.testing.mapper.PettyCashMapper;
import com.springboot.fxn.testing.model.ClientAccount;
import com.springboot.fxn.testing.model.PettyCash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TestingApplicationTests {

	@Autowired
	private AccountMapper accountMapper;  // Injected for testing
	@Autowired
	private PettyCashMapper pettyCashMapper;  // Injected for testing

	@Test
	void contextLoads() {
		assertNotNull(pettyCashMapper);
		assertNotNull(accountMapper);
	}

	@Test
	public void testAccountEntityToDtoMapping() {
		// Arrange
		ClientAccount clientAccount = new ClientAccount();
		clientAccount.setId(1L);
		clientAccount.setName("John Doe");
		clientAccount.setEmail("john@mail.com");

		// Act
		ClientAccountDto dto = accountMapper.toAccountDto(clientAccount);

		// Assert
		assertEquals(clientAccount.getId(), dto.getId()); // Uncomment if ClientAccountDto has id field
		assertEquals(clientAccount.getName(), dto.getName());
		assertEquals(clientAccount.getEmail(), dto.getEmail());
	}

	@Test
	public void testAccountDtoToEntityMapping() {
		// Arrange
		ClientAccountDto dto = new ClientAccountDto();
		dto.setId(1L);
		dto.setName("Mary Public");
		dto.setEmail("mary@mail.com");

		// Act
		ClientAccount clientAccount = accountMapper.toAccountEntity(dto);

		//assertEquals(dto.getId(), account.getId());
		assertEquals(dto.getName(), clientAccount.getName());
		assertEquals(dto.getEmail(), clientAccount.getEmail());
	}

	@Test
	public void testPettyCashEntityToDto() {
		ClientAccount clientAccount1 = new ClientAccount();
		clientAccount1.setId(1L);
		clientAccount1.setName("John Doe");
		clientAccount1.setEmail("john@mail.com");

		ClientAccount clientAccount2 = new ClientAccount();
		clientAccount2.setId(2L);
		clientAccount2.setName("Mary Public");
		clientAccount2.setEmail("mary@mail.com");

		PettyCash pettyCash = new PettyCash();
		pettyCash.setId(1L);
		pettyCash.setDate(new Date());
		pettyCash.setActivityCategory("Activity 1");
		pettyCash.setSoaCategory("Out-Of-Pocket");
		pettyCash.setActivityDescription("Sample Description");
		pettyCash.setAccounts(Set.of(clientAccount1, clientAccount2));

		PettyCashDto dto = pettyCashMapper.toPettyCashDto(pettyCash);
		assertEquals(Set.of(1L, 2L), dto.getAccountIds(), () -> "Set Id's should match");


	}

	/* Default
	@Test
	void contextLoads() {
	} */

}
