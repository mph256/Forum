<section class="section-profile" id="section-profile">

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
							<s:text name="breadcrumb.profile"/>	
						</li>
					</ol>
				</nav>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-12">
				<div class="card text-center">
					<div class="card-body">
						<div class="row">
							<div class="col-12">
								<img src="/Forum/assets/img/users/${ sessionScope.user.profilePicture.name }" alt="<s:text name="img.alt.profile"/>" class="img-fluid rounded-circle">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-12 offset-md-1 col-md-10 offset-lg-2 col-lg-8 offset-xl-3 col-xl-6">
								<div class="row">
									<label for="static-login" class="col-6 col-form-label-sm text-end">
										<s:text name="label.login"/>
									</label>
									<div class="col-5">			 	
										 <input type="text" class="form-control-sm form-control-plaintext" id="static-login" name="static-login" value="<s:property value="#session.user.login"/>" readonly>
									</div>
								</div>
								<div class="row">
									<label for="static-email" class="col-6 col-form-label-sm text-end">
										<s:text name="label.email"/>
									</label>
									<div class="col-5">			 	
										 <input type="text" class="form-control-sm form-control-plaintext" id="static-email" name="static-email" value="<s:property value="#session.user.email"/>" readonly>
									</div>
								</div>
								<div class="row">
									<label for="static-password" class="col-6 col-form-label-sm text-end">
										<s:text name="label.password"/>
									</label>
									<div class="col-5">			 	
										 <input type="password" class="form-control-sm form-control-plaintext" id="static-password" name="static-password" value="<s:property value="#session.user.password"/>" readonly>
									</div>
									<div class="col-1">	
										<button type="button" class="btn btn-sm btn-primary btn-show-password" id="btn-show-password" name="btn-show-password">
											<i class="bi bi-eye"></i>
										</button>
									</div>
								</div>
								<div class="row">
									<label for="static-date-registration" class="col-6 col-form-label-sm text-end">
										<s:text name="label.date.registration"/>
									</label>
									<div class="col-5">
										<input type="date" class="form-control-sm form-control-plaintext" id="static-date-registration" name="static-date-registration" value="<s:text name="format.date.input"><s:param value="#session.user.registrationDate"/></s:text>" readonly>
									</div>
								</div>
								<div class="row">
									<label for="static-date-connection-last" class="col-6 col-form-label-sm text-end">
										<s:text name="label.date.connection.last"/>
									</label>
									<div class="col-5">
										<input type="datetime-local" class="form-control-sm form-control-plaintext" id="static-date-connection-last" name="static-date-connection-last" value="<s:text name="format.datetime.input"><s:param value="#session.user.lastConnection"/></s:text>" readonly>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col-6 text-end">
										<button class="btn btn-sm btn-primary btn-show-form-edit-account" type="button" data-bs-toggle="collapse" data-bs-target="#collapse" aria-expanded="false" aria-controls="collapse">
				    						<s:text name="btn.update"/>
				  						</button>
				  					</div>
				  					<div class="col-6">
								  		<s:form method="post" theme="simple" cssClass="form-delete-account text-start" id="form-delete-account">
								  			<s:submit cssClass="btn btn-sm btn-danger btn-delete-account" id="btn-delete-account" value="%{getText('btn.delete')}" method="deleteAccount" data-bs-toggle="modal" data-bs-target="#modal"/>
								  		</s:form>
								  	</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		  	</div>	
	  	</div>

	  	<div class="row mt-3">
	  		<div class="col-12">
	  			<div class="collapse" id="collapse">
					<s:form method="post" enctype="multipart/form-data" theme="bootstrap" cssClass="form-edit-account" id="form-edit-account">
						<div class="row">
							<div class="col-12">
								<s:textfield cssClass="form-control-sm" id="email" name="email" value="%{#session.user.email}" placeholder="%{getText('label.email')}"/>
								<s:fielderror fieldName="email" cssClass="mt-2 mb-0"/>
							</div>
						</div>
						<div class="row">
							<div class="col-6">
								<s:password cssClass="form-control-sm" id="password" name="password" value="%{#session.user.password}" showPassword="true" placeholder="%{getText('label.password')}"/>
								<s:fielderror fieldName="password" cssClass="mt-2 mb-0"/>
							</div>
							<div class="col-6">
								<s:password cssClass="form-control-sm" id="passwordConfirmation" name="passwordConfirmation" value="%{#session.user.password}" showPassword="true" placeholder="%{getText('label.password.confirmation')}"/>
								<s:fielderror fieldName="passwordConfirmation" cssClass="mt-2 mb-0"/>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<s:file accept="image/*" cssClass="form-control-sm" id="profilePicture" name="profilePicture" value="%{#session.user.profilePicture}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<s:submit cssClass="btn btn-sm btn-primary" id="btn-edit-account" value="%{getText('btn.submit')}" method="editAccount"/>
							</div>
						</div>
					</s:form>
			  	</div>
			</div>
		</div>

		<div class="modal" id="modal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title"><s:text name="title.form.delete.account"/></h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p><s:text name="confirm.delete.account"/></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal"><s:text name="btn.cancel"/></button>
						<button type="button" class="btn btn-sm btn-danger btn-delete-account-confirmation"><s:text name="btn.delete"/></button>
					</div>
				</div>
			</div>
		</div>

  	</div>

</section>