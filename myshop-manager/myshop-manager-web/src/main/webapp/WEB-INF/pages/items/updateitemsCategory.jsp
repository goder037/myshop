<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>

<div class="row">
	<div class="col-lg-12">
		<form class="form-horizontal valid-form" id="itemCategoryUpdateForm" action="javascript:void(0)" method="post">
			<div class="row">
				<div class="col-lg-7">
					<div class="form-group">
						<label for="itemCategoryName" class="col-lg-5 control-label">类别名称:</label>
						<div class="col-lg-7">
							<input name="name" class="form-control" type="text" maxlength="10" data-required="require" data-pattern="" data-toggle="tooltip" title="必填项，中文或英文字符，最长10位">
						</div>
					</div>
					<div class="form-group">
						<label for="rank" class="col-lg-5 control-label">类别排序值:</label>
						<div class="col-lg-7">
							<input name="rank" class="form-control" type="text" maxlength="3" data-pattern="^[1-9][1-9]{0,2}$" data-required="require" data-toggle="tooltip" title="必填项，数字，最长3位">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.col-lg-12 -->
</div>

