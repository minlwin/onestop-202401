package com.jdc.onestop.hospital.domain.member.repo;

import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Account;

public interface AccountRepo extends BaseRepository<Account, String> {

	Optional<Account> findOneByUsername(String name);

}
