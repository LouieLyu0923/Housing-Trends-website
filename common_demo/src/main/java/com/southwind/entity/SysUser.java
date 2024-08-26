package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-08-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 账号
     */
      private String userName;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */

      private String nickName;

      /**
     * 手机号
     */
      private String mobile;

      /**
     * 是否删除 0正常 1删除
     */
      @TableLogic
      private Integer isDeleted = 0;

      /**
     * 账号类型  1 系统账号 2 员工 3 候选人 ...
     */
      private Integer accountType;

      /**
     * 账号状态 0 可用  1冻结
     */
      private Integer accountState;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 更新时间
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      /**
     * 用户权限
     */
      private String userRole;

      /**
     * 用户权限
     */
      private String updateUser;

      /**
     * 创建人
     */
      private String createUser;


}
