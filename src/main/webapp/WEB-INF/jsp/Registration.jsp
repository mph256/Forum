<section class="section-registration" id="section-registration">

	<div class="container">

		<div class="row">
			<div class="col-12">
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">
							<s:url namespace="/" action="home" var="url"/>
							<s:a href="%{url}">
								<s:text name="breadcrumb.home"/>
							</s:a>
						</li>
						<li class="breadcrumb-item active" aria-current="page">
							<s:text name="breadcrumb.registration"/>
						</li>
					</ol>
				</nav>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<s:form method="post" theme="bootstrap" cssClass="form-registration" id="form-registration">
					<div class="row">
						<div class="col-12">
							<s:textfield cssClass="form-control-sm" id="login" name="login" placeholder="%{getText('label.login')}"/>
							<s:fielderror fieldName="login" cssClass="mt-2 mb-0"/>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<s:textfield cssClass="form-control-sm" id="email" name="email" placeholder="%{getText('label.email')}"/>
							<s:fielderror fieldName="email" cssClass="mt-2 mb-0"/>
						</div>
					</div>
					<div class="row">
						<div class="col-6">
							<s:password cssClass="form-control-sm" id="password" name="password" placeholder="%{getText('label.password')}"/>
							<s:fielderror fieldName="password" cssClass="mt-2 mb-0"/>
						</div>
						<div class="col-6">
							<s:password cssClass="form-control-sm" id="passwordConfirmation" name="passwordConfirmation" placeholder="%{getText('label.password.confirmation')}"/>
							<s:fielderror fieldName="passwordConfirmation" cssClass="mt-2 mb-0"/>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<s:submit cssClass="btn btn-sm btn-primary" id="btn-registration" value="%{getText('btn.submit')}" method="execute"/>
						</div>
					</div>
				</s:form>
			</div>
		</div>

	</div>

</section>