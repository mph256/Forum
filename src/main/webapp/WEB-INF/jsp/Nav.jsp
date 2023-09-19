<nav class="navbar navbar-expand-md fixed-top nav">
	<div class="container">
		<s:url namespace="/" action="home" var="url"/>
		<s:a class="navbar-brand fs-4 fw-bold" href="%{url}">Forum</s:a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end" id="nav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<s:url action="home" var="url"/>
					<s:a class="nav-link" href="%{url}">
						<i class="bi bi-house"></i>
					</s:a>
				</li>
				<s:if test="#session.user == null">
					<li class="nav-item">
						<s:form action="connection" method="post" theme="simple" cssClass="nav-link form-redirect-connection pe-0" id="form-redirect-connection">
							<s:submit type="button" cssClass="btn btn-sm p-0" id="btn-redirect-connection" value="" method="loadPage">
								<i class="bi bi-arrow-bar-left"></i>
							</s:submit>
						</s:form>
					</li>	
				</s:if>
				<s:else>
					<li class="nav-item">
						<s:form action="profile" method="post" theme="simple" cssClass="nav-link form-redirect-profile" id="form-redirect-profile">
							<s:submit type="button" cssClass="btn btn-sm p-0" id="btn-redirect-profile" value="" method="loadPage">
								<img src="/Forum/assets/img/users/${ sessionScope.user.profilePicture.name }" alt="<s:text name="img.alt.profile"/>" class="img-fluid rounded-circle">
							</s:submit>
						</s:form>
					</li>
					<li class="nav-item">
						<s:url action="disconnection" var="url"/>
						<s:a class="nav-link pe-0" href="%{url}">
							<i class="bi bi-arrow-bar-right"></i>
						</s:a>
					</li>
				</s:else>
			</ul>
		</div>
	</div>
</nav>