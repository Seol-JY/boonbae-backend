package com.agebra.boonbaebackend.repository;

import com.agebra.boonbaebackend.domain.Funding;
import com.agebra.boonbaebackend.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    // 현재 진행중인 해당 user의 펀딩을 최신순으로 조회
    @Query("SELECT f FROM Funding f WHERE f.closeDate >= CURRENT_DATE() ORDER BY f.createAt ASC")
    List<Funding> findOngoingFundingByUser(Users users);
    List<Funding> findByUser(Users user);

    //@Query(value = "SELECT p FROM Funding p WHERE p.isApproved= :check")
    //Page<Funding> findByApproved(Pageable pageable, boolean check);

    @Query(value = "SELECT p FROM Funding p WHERE p.isApproved= :check ORDER BY p.createAt desc ")
    List<Funding> findByApproved(boolean check);

    List<Funding> findByIsApprovedTrueOrderByOpenDateDesc();

    //후원자 수 많은 순
    @Query("SELECT f FROM Funding f ORDER BY (f.currentAmount / f.supportingAmount) DESC")
    List<Funding> findAllByOrderByCurrentAmountDivSupportingAmount();

    //마감임박 순
    List<Funding> findAllByOrderByCloseDateAsc();

}
