package me.timur.touroperatorbpa.respository;

import me.timur.touroperatorbpa.TestIntegrationConfiguration;
import me.timur.touroperatorbpa.domain.entity.Company;
import me.timur.touroperatorbpa.domain.entity.Group;
import me.timur.touroperatorbpa.domain.entity.Role;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.model.enums.GroupStatus;
import me.timur.touroperatorbpa.domain.repository.CompanyRepository;
import me.timur.touroperatorbpa.domain.repository.GroupRepository;
import me.timur.touroperatorbpa.domain.repository.RoleRepository;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.domain.repository.impl.GroupFilteredFetchRepository;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.util.Pair;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Temurbek Ismoilov on 29/07/23.
 */

@SpringBootTest
@Import(TestIntegrationConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class GroupFilteredFetchRepositoryTest {

    @Autowired
    private GroupFilteredFetchRepository groupCustomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    private User tourOperator;
    private Company company1;
    private Company company2;
    private Group group1;
    private Group group2;
    @Test
    public void testByTourOperatorId() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setTourOperatorId(tourOperator.getId());
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        filter2.setTourOperatorId(999L);
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();

        // THEN 1
        assertEquals(2, groups1.size());
        assertEquals(Long.valueOf(2), count1);
        assertEquals(tourOperator.getId(), groups1.get(0).getTourOperator().getId());
        // THEN 2
        assertEquals(0, groups2.size());
        assertEquals(Long.valueOf(0), count2);
        // THEN 3
        assertEquals(2, groups3.size());
        assertEquals(Long.valueOf(2), count3);
    }

    @Test
    public void testByCountry() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setCountry(group1.getCountry());
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();
        filter3.setCountry("NON_EXISTING_COUNTRY");

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();

        // THEN 1
        assertEquals(1, groups1.size());
        assertEquals(Long.valueOf(1), count1);
        assertEquals(group1.getCountry(), groups1.get(0).getCountry());
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);
        // THEN 3
        assertEquals(0, groups3.size());
        assertEquals(Long.valueOf(0), count3);
    }

    @Test
    public void testByCompany() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setCompanyId(group1.getCompany().getId());
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();
        filter3.setCompanyId(999L);

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();

        // THEN 1
        assertEquals(1, groups1.size());
        assertEquals(Long.valueOf(1), count1);
        assertEquals(group1.getCompany().getId(), groups1.get(0).getCompany().getId());
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);
        // THEN 3
        assertEquals(0, groups3.size());
        assertEquals(Long.valueOf(0), count3);
    }

    @Test
    public void testByArrival() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setArrivalFrom(group2.getArrival());
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();
        filter3.setArrivalFrom(LocalDateTime.now().plusYears(10));
        // GIVEN 4
        GroupFilter filter4 = new GroupFilter();
        filter4.setArrivalTo(LocalDateTime.now().plusMonths(2));

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();
        // WHEN 4
        Pair<List<Group>, Long> result4 = groupCustomRepository.findAllFiltered(filter4);
        List<Group> groups4 = result4.getFirst();
        Long count4 = result4.getSecond();

        // THEN 1
        assertEquals(1, groups1.size());
        assertEquals(Long.valueOf(1), count1);
        assertEquals(group2.getArrival(), groups1.get(0).getArrival());
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);
        // THEN 3
        assertEquals(0, groups3.size());
        assertEquals(Long.valueOf(0), count3);
        // THEN 4
        assertEquals(1, groups4.size());
        assertEquals(Long.valueOf(1), count4);
        assertEquals(group1.getArrival(), groups4.get(0).getArrival());
    }

    @Test
    public void testByStatus() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setStatus(GroupStatus.ACTIVE);
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();

        // THEN 1
        assertEquals(1, groups1.size());
        assertEquals(Long.valueOf(1), count1);
        assertEquals(GroupStatus.ACTIVE, groups1.get(0).getStatus());
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);

    }

    @Test
    public void testByPageNumber() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setPageNumber(1);
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();
        filter3.setPageNumber(100);

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();

        // THEN 1
        assertEquals(0, groups1.size());
        assertEquals(Long.valueOf(2), count1);
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);
        // THEN 3
        assertEquals(0, groups3.size());
        assertEquals(Long.valueOf(2), count3);
    }

    @Test
    public void testByPageSize() {
        // GIVEN 1
        GroupFilter filter1 = new GroupFilter();
        filter1.setPageSize(1);
        // GIVEN 2
        GroupFilter filter2 = new GroupFilter();
        // GIVEN 3
        GroupFilter filter3 = new GroupFilter();
        filter3.setPageSize(0);

        // WHEN 1
        Pair<List<Group>, Long> result1 = groupCustomRepository.findAllFiltered(filter1);
        List<Group> groups1 = result1.getFirst();
        Long count1 = result1.getSecond();
        // WHEN 2
        Pair<List<Group>, Long> result2 = groupCustomRepository.findAllFiltered(filter2);
        List<Group> groups2 = result2.getFirst();
        Long count2 = result2.getSecond();
        // WHEN 3
        Pair<List<Group>, Long> result3 = groupCustomRepository.findAllFiltered(filter3);
        List<Group> groups3 = result3.getFirst();
        Long count3 = result3.getSecond();

        // THEN 1
        assertEquals(1, groups1.size());
        assertEquals(Long.valueOf(2), count1);
        // THEN 2
        assertEquals(2, groups2.size());
        assertEquals(Long.valueOf(2), count2);
        // THEN 3
        assertEquals(0, groups3.size());
        assertEquals(Long.valueOf(2), count3);
    }

    @BeforeEach
    public void getSetup() {
        Role role = new Role("ROLE_TOUR_OPERATOR");
        roleRepository.save(role);

        tourOperator = createUser(List.of(role));
        company1 = createCompany(1L,"Company A");
        company2 = createCompany(2L,"Company B");

        LocalDateTime arrival1 = LocalDateTime.now().plusMonths(2);
        LocalDateTime arrival2 = LocalDateTime.now().plusMonths(3);
        group1 = createGroup(1L,"Group 1", "Country A", company1, tourOperator, arrival1, GroupStatus.ACTIVE);
        group2 = createGroup(2L,"Group 2", "Country B", company2, tourOperator, arrival2, GroupStatus.CANCELLED);

    }

    // Helper methods to create entities
    private User createUser(List<Role> roles) {
        User user = new User();
        user.setRoles(roles);
        user.setDateCreated(LocalDateTime.now());
        user.setId(1L);
        user.setFirstName("John Doe");

        userRepository.save(user);

        return user;
    }

    private Company createCompany(Long id, String name) {
        Company company = new Company();
        company.setName(name);
        company.setDateCreated(LocalDateTime.now());
        company.setId(id);

        companyRepository.save(company);

        return company;
    }

    private Group createGroup(Long id, String number, String country, Company company, User tourOperator, LocalDateTime arrival, GroupStatus status) {
        Group group = new Group();
        group.setId(id);
        group.setSize(3);
        group.setTourLeaderAmount(0);
        group.setStatus(GroupStatus.ACTIVE);
        group.setNumber(number);
        group.setCountry(country);
        group.setCompany(company);
        group.setTourOperator(tourOperator);
        group.setArrival(arrival);
        group.setDateCreated(LocalDateTime.now());
        group.setStatus(status);

        groupRepository.save(group);

        return group;
    }
}
