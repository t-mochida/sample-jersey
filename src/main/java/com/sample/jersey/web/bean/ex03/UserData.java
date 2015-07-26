package com.sample.jersey.web.bean.ex03;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserData {
	@DecimalMin(value="1", inclusive=true, message="{user.id.min}")//ユーザIDは{value}以上の値を入力してください
	@DecimalMax(value="10", inclusive=false, message="{user.id.max}")//ユーザIDは{value}未満の値を入力してください
	public Integer id;

    @Pattern(regexp = "[0-9a-z]+", message = "{user.name.pattern}")//ユーザ名は英数字小文字のみで入力してください
    @Size(min = 4, max = 8, message = "{user.name.size}")//ユーザ名は{min}以上{max}以下の文字数で入力してください
	public String name;

    @NotNull(message = "{user.diskusage.notnull}")//ディスク使用率は入力必須です
    @Digits(integer = 3, fraction = 2,  message = "{user.diskusage.digits}")//ディスク使用率は小数点以下２位までのパーセントで入力してください
    public Double diskUsage;

	@NotBlank(message = "{user.email.notblank}")//メールアドレスは入力必須です
	@Email(message = "{user.email.pattern}")//メールアドレスを正しく入力してください
	public String email;

	public UserData(){}

	public UserData(Integer id, String name, String diskUsage, String email) {
		this.id = id;
		this.name = name;
		this.diskUsage = Double.parseDouble(diskUsage);
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserData [id=").append(id)
			.append(", name=").append(name)
			.append(", diskUsage=").append(diskUsage)
			.append(", email=").append(email).append("]");
		return builder.toString();
	}
}
