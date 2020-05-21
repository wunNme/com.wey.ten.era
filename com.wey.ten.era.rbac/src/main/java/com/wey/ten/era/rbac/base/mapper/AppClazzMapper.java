package com.wey.ten.era.rbac.base.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wey.ten.era.rbac.base.entity.AppClazz;

@Mapper
public interface AppClazzMapper {

	public List<AppClazz> selectAppClazzList(AppClazz appClazz);

	public AppClazz selectAppClazzById(Long appClazzId);

	public List<AppClazz> selectChildrenAppClazzById(Long appClazzId);

	public int selectNormalChildrenAppClazzById(Long appClazzId);

	public int hasChildByAppClazzId(Long appClazzId);

	public int checkAppClazzExistUser(Long appClazzId);

	public AppClazz checkAppClazzNameUnique(@Param("clazzName") String deptName, @Param("parentId") Long parentId);

	public int insertAppClazz(AppClazz appClazz);

	public int updateAppClazz(AppClazz appClazz);

	public void updateAppClazzStatus(AppClazz appClazz);

	public int updateAppClazzChildren(@Param("appClazzs") List<AppClazz> appClazzs);

	public int deleteAppClazzById(Long appClazzId);
}
