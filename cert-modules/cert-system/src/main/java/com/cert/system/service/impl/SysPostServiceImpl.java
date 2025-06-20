package com.cert.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import com.cert.common.core.constant.SystemConstants;
import com.cert.common.core.exception.ServiceException;
import com.cert.common.core.service.PostService;
import com.cert.common.core.utils.MapstructUtils;
import com.cert.common.core.utils.StreamUtils;
import com.cert.common.core.utils.StringUtils;
import com.cert.common.mybatis.core.page.PageQuery;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.system.domain.SysDept;
import com.cert.system.domain.SysPost;
import com.cert.system.domain.SysUserPost;
import com.cert.system.domain.bo.SysPostBo;
import com.cert.system.domain.vo.SysPostVo;
import com.cert.system.mapper.SysDeptMapper;
import com.cert.system.mapper.SysPostMapper;
import com.cert.system.mapper.SysUserPostMapper;
import com.cert.system.service.ISysPostService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位信息 服务层处理
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysPostServiceImpl implements ISysPostService, PostService {

    private final SysPostMapper baseMapper;
    private final SysDeptMapper deptMapper;
    private final SysUserPostMapper userPostMapper;

    @Override
    public TableDataInfo<SysPostVo> selectPagePostList(SysPostBo post, PageQuery pageQuery) {
        Page<SysPostVo> page = baseMapper.selectPagePostList(pageQuery.build(), buildQueryWrapper(post));
        return TableDataInfo.build(page);
    }

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPostVo> selectPostList(SysPostBo post) {
        return baseMapper.selectVoList(buildQueryWrapper(post));
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 岗位ID
     */
    @Override
    public List<SysPostVo> selectPostsByUserId(Long userId) {
        return baseMapper.selectPostsByUserId(userId);
    }

    /**
     * 根据查询条件构建查询包装器
     *
     * @param bo 查询条件对象
     * @return 构建好的查询包装器
     */
    private LambdaQueryWrapper<SysPost> buildQueryWrapper(SysPostBo bo) {
        LambdaQueryWrapper<SysPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(bo.getPostCode()), SysPost::getPostCode, bo.getPostCode())
            .like(StringUtils.isNotBlank(bo.getPostCategory()), SysPost::getPostCategory, bo.getPostCategory())
            .like(StringUtils.isNotBlank(bo.getPostName()), SysPost::getPostName, bo.getPostName())
            .eq(StringUtils.isNotBlank(bo.getStatus()), SysPost::getStatus, bo.getStatus())
            .orderByAsc(SysPost::getPostSort);
        if (ObjectUtil.isNotNull(bo.getDeptId())) {
            //优先单部门搜索
            wrapper.eq(SysPost::getDeptId, bo.getDeptId());
        } else if (ObjectUtil.isNotNull(bo.getBelongDeptId())) {
            //部门树搜索
            wrapper.and(x -> {
                List<SysDept> deptList = deptMapper.selectListByParentId(bo.getBelongDeptId());
                List<Long> deptIds = StreamUtils.toList(deptList, SysDept::getDeptId);
                deptIds.add(bo.getBelongDeptId());
                x.in(SysPost::getDeptId, deptIds);
            });
        }
        return wrapper;
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPostVo> selectPostAll() {
        return baseMapper.selectVoList(new QueryWrapper<>());
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPostVo selectPostById(Long postId) {
        return baseMapper.selectVoById(postId);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        List<SysPostVo> list = baseMapper.selectPostsByUserId(userId);
        return StreamUtils.toList(list, SysPostVo::getPostId);
    }

    /**
     * 通过岗位ID串查询岗位
     *
     * @param postIds 岗位id串
     * @return 岗位列表信息
     */
    @Override
    public List<SysPostVo> selectPostByIds(List<Long> postIds) {
        return baseMapper.selectVoList(new LambdaQueryWrapper<SysPost>()
            .select(SysPost::getPostId, SysPost::getPostName, SysPost::getPostCode)
            .eq(SysPost::getStatus, SystemConstants.NORMAL)
            .in(CollUtil.isNotEmpty(postIds), SysPost::getPostId, postIds));
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostNameUnique(SysPostBo post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostName, post.getPostName())
            .eq(SysPost::getDeptId, post.getDeptId())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        return !exist;
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostCodeUnique(SysPostBo post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostCode, post.getPostCode())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        return !exist;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public long countUserPostById(Long postId) {
        return userPostMapper.selectCount(new LambdaQueryWrapper<SysUserPost>().eq(SysUserPost::getPostId, postId));
    }

    /**
     * 通过部门ID查询岗位使用数量
     *
     * @param deptId 部门id
     * @return 结果
     */
    @Override
    public long countPostByDeptId(Long deptId) {
        return baseMapper.selectCount(new LambdaQueryWrapper<SysPost>().eq(SysPost::getDeptId, deptId));
    }

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId) {
        return baseMapper.deleteById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysPost post = baseMapper.selectById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s已分配，不能删除!", post.getPostName()));
            }
        }
        return baseMapper.deleteByIds(Arrays.asList(postIds));
    }

    /**
     * 新增保存岗位信息
     *
     * @param bo 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPostBo bo) {
        SysPost post = MapstructUtils.convert(bo, SysPost.class);
        return baseMapper.insert(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param bo 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPostBo bo) {
        SysPost post = MapstructUtils.convert(bo, SysPost.class);
        return baseMapper.updateById(post);
    }

}
