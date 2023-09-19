<section class="section-connection" id="section-connection">

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
							<s:text name="breadcrumb.connection"/>
						</li>
					</ol>
				</nav>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<s:form method="post" theme="bootstrap" cssClass="form-connection" id="form-connection">
					<div class="row">
						<div class="col-12">
							<s:textfield cssClass="form-control-sm" id="login" name="login" placeholder="%{getText('label.login')}"/>
							<s:fielderror fieldName="login" cssClass="mt-2 mb-0"/>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<s:password cssClass="form-control-sm" id="password" name="password" placeholder="%{getText('label.password')}"/>
							<s:fielderror fieldName="password" cssClass="mt-2 mb-0"/>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<s:submit cssClass="btn btn-sm btn-primary" id="btn-connection" value="%{getText('btn.submit')}" method="execute"/>
						</div>
					</div>
				</s:form>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-12">
				<s:form action="registration" method="post" theme="simple" cssClass="form-redirect-registration" id="form-redirect-registration">
					<s:submit cssClass="btn btn-sm btn-link p-0" id="btn-redirect-registration" key="link.registration" method="loadPage"/>
				</s:form>
			</div>
		</div>

	</div>

</section>