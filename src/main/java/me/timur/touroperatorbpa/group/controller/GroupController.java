package me.timur.touroperatorbpa.group.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.group.model.GroupCreateDto;
import me.timur.touroperatorbpa.group.model.GroupDto;
import me.timur.touroperatorbpa.group.model.GroupFilter;
import me.timur.touroperatorbpa.group.service.GroupService;
import me.timur.touroperatorbpa.model.PageableList;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.model.response.NoopDTO;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Temurbek Ismoilov on 03/08/23.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping(value = {"", "/"})
    public BaseResponse<GroupDto> create(@Valid @RequestBody GroupCreateDto groupCreateDto) {
        return BaseResponse.ok(groupService.create(groupCreateDto));
    }

    @GetMapping("/{id}")
    public BaseResponse<GroupDto> get(@PathVariable Long id) {
        return BaseResponse.ok(groupService.get(id));
    }

    @PutMapping(value = {"", "/"})
    public BaseResponse<GroupDto> update(@Valid @RequestBody GroupDto groupDto) {
        return BaseResponse.ok(groupService.update(groupDto));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<NoopDTO> cancel(@PathVariable Long id) {
        groupService.cancel(id);
        return BaseResponse.ok();
    }

    @PostMapping("/filter")
    public BaseResponse<PageableList<GroupDto>> getAllByFiltered(@RequestBody GroupFilter groupFilter) {
        return BaseResponse.ok(groupService.getAllByFiltered(groupFilter));
    }
}
