package com.carelinker.core.pojo.carelinker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liyue
 * @date 2019-08-21 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ttd_user", catalog = "carelinker")
public class TTDUser {

    @Id
    @Column(name = "patient_id", unique = true, nullable = false, length = 32)
    private String patient_id;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "phone")
    private String phone;

    // 0表示无效 1表示有效
    @Column(name = "is_valid", length = 1)
    private Integer isValid;
}
