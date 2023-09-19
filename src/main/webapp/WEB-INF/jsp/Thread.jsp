<section class="section-thread" id="section-thread">

	<s:set var="numberOfPosts" value="thread.posts.size"/>
	<s:set var="numberOfPostsToShowPerPage" value="5"/>
	<s:set var="currentPage" value="1"/>

	<input type="hidden" id="posts-length" name="posts-length" value="<s:property value="numberOfPosts"/>">
	<input type="hidden" id="posts-page-length" name="posts-page-length" value="<s:property value="numberOfPostsToShowPerPage"/>">
	<input type="hidden" id="page-current" name="page-current" value="<s:property value="#currentPage"/>">

	<input type="hidden" id="thread-id" name="thread-id" value="<s:property value="thread.id"/>">

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
							<s:text name="breadcrumb.thread"><s:param value="thread.id"/></s:text>	
						</li>
					</ol>
				</nav>
			</div>
		</div>

		<div class="row">
			<div class="col-1">
				<p class="lead"><s:text name="label.thread.title"/></p>
			</div>
			<div class="col-11 ps-0">
				<s:if test="%{(#session.user != null) && (thread.user == #session.user)}">
					<div class="row">
						<div class="col-11">
							<h4 class="h4 thread-title"><s:property value="thread.title"/></h4>
						</div>
						<div class="col-1">
							<div class="d-flex justify-content-end">
								<s:form action="thread" method="post" theme="simple" cssClass="form-delete-thread" id="form-delete-thread">
									<s:hidden id="%{'thread-' + thread.id + '-id'}" name="id" value="%{thread.id}"/>
									<s:submit type="button" cssClass="btn btn-sm btn-danger btn-delete-thread" id="btn-delete-thread" value="" method="deleteThread">
										<i class="bi bi-x-lg"></i>
									</s:submit>
								</s:form>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<h4 class="h4 thread-title"><s:property value="thread.title"/></h4>
				</s:else>
			</div>
		</div>

		<div class="row">
			<div class="col-1">
				<p class="lead fs-6"><s:text name="label.thread.tags"/></p>
			</div>
			<div class="col-11 ps-0">
				<div class="thread-tags">
					<s:iterator value="thread.tags" var="tag">
						<span class="badge bg-secondary">
							<s:property value="#tag.label"/>
						</span>
					</s:iterator>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="offset-11 col-1">
				<div class="d-flex justify-content-end">
					<button type="button" class="btn btn-sm btn-primary h-100 btn-refresh-posts" id="btn-refresh-posts" name="btn-refresh-posts">
						<i class="bi bi-arrow-clockwise"></i>
					</button>
				</div>
			</div>
		</div>

		<div id="posts" class="mt-3">
			<s:iterator value="thread.posts" var="post" status="status">
				<s:if test="(#status.index >= (#currentPage - 1) * #numberOfPostsToShowPerPage) && (#status.index <= ((#currentPage - 1) * #numberOfPostsToShowPerPage) + #numberOfPostsToShowPerPage - 1)">
					<div id="post-<s:property value="%{#post.id}"/>">
						<div class="card mb-3">
							<div class="card-header">
								<div class="row">
									<div class="col-1">
										<img src="/Forum/assets/img/users/${ post.user.profilePicture.name }" alt="<s:text name="img.alt.user.profile"><s:param value="#post.user.login"/></s:text>" class="rounded-circle">
									</div>
									<div class="col-11">
										<div class="row">
											<div class="col-8 col-md-9 col-lg-10 ps-0">
												<span class="fw-semibold post-user-login">
													<s:property value="%{#post.user.login}"/>
												</span>
											</div>
											<div class="col-4 col-md-3 col-lg-2">
												<s:if test="%{(#session.user != null) && (#post.user == #session.user)}">
													<div class="row">
														<div class="offset-6 col-3 offset-xl-8 col-xl-2">
															<button type="button" class="btn btn-sm btn-show-form-edit-post p-0" id="<s:property value="%{'btn-show-form-edit-post-' + #post.id}"/>" name="btn-show-form-edit-post">
																<i class="bi bi-pencil-square"></i>
															</button>
														</div>
														<div class="col-3 col-xl-2">
															<s:form action="deletePost" method="post" theme="simple" cssClass="form-delete-post" id="%{'form-delete-post-' + #post.id}">
																<s:hidden id="%{'post-' + #post.id + '-id'}" name="id" value="%{#post.id}"/>
																<s:submit type="button" cssClass="btn btn-sm btn-delete-post p-0" id="%{'btn-delete-post-' + #post.id}" value="">
																	<i class="bi bi-x-lg"></i>
																</s:submit>
															</s:form>
														</div>
													</div>
												</s:if>
											</div>
										</div>									
										<div class="row">
											<s:if test="#post.publicationDate != #post.lastUpdate">
												<div class="col-6 col-md-5 col-lg-3 col-xxl-2 ps-0">
													<span class="post-date-publication">
														<s:text name="format.datetime">
															<s:param value="#post.publicationDate"/>
														</s:text>
													</span>
												</div>
												<div class="col-6 col-md-7 col-lg-9 col-xxl-10 ps-0">
													<span class='fw-light post-date-update'>
														<s:text name="format.datetime">
															<s:param value="#post.lastUpdate"/>
														</s:text>
													</span>
												</div>
											</s:if>
											<s:else>
												<div class="col-12 ps-0">
													<span class='post-date-publication'>
														<s:text name="format.datetime">
															<s:param value="#post.publicationDate"/>
														</s:text>
													</span>
												</div>
											</s:else>
										</div>
									</div>							
								</div>
							</div>
							<div class="card-body">
								<div id="post-<s:property value="%{#post.id}"/>-content">
									<p class="card-text">
										<s:property value="%{#post.content}"/>
									</p>
								</div>
							</div>
						</div>
					</div>
				</s:if>
			</s:iterator>
		</div>

		<div class="pagination-posts" id="pagination-posts">
			<s:if test="#numberOfPosts > #numberOfPostsToShowPerPage">
				<nav aria-label="Pagination">
					<ul class="pagination pagination-sm justify-content-end">
						<s:if test="#currentPage != 1">
							<li class="page-item">
								<a class="page-link" href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</s:if>
						<s:else>
							<li class="page-item disabled">
								<a class="page-link" href="#" aria-label="Previous" aria-disabled="true">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</s:else>
						<s:iterator var="i"
							begin="1"
							end="%{(#numberOfPosts % #numberOfPostsToShowPerPage == 0)?(#numberOfPosts / #numberOfPostsToShowPerPage):(#numberOfPosts / #numberOfPostsToShowPerPage + 1)}">
	 						<s:if test="#currentPage == #i">
	 							<li class="page-item active" aria-current="page">
	 								<a class="page-link" href="#"><s:property value="#i"/></a>
	 							</li>
	 						</s:if>
	 						<s:else>
	 							<li class="page-item">
	 								<a class="page-link" href="#"><s:property value="#i"/></a>
	 							</li>
	 						</s:else>
			  			</s:iterator>
			  			<s:if test="#currentPage != ((#numberOfPosts % #numberOfPostsToShowPerPage == 0)?(#numberOfPosts / #numberOfPostsToShowPerPage):(#numberOfPosts / #numberOfPostsToShowPerPage + 1))">
							<li class="page-item">
		 						<a class="page-link" href="#" aria-label="Next">
		 					        <span aria-hidden="true">&raquo;</span>
		 						</a>
		 					</li>
		 				</s:if>
		 				<s:else>
		 					<li class="page-item disabled">
		 						<a class="page-link" href="#" aria-label="Next" aria-disabled="true">
		 					        <span aria-hidden="true">&raquo;</span>
		 						</a>
		 					</li>
		 				</s:else>
	 				</ul>
	 			</nav>
			</s:if>
		</div>

		<s:if test="#session.user != null">
			<div class="row">
				<div class="col-12">
					<h5 class="h5"><s:text name="title.form.create.post"/></h5>
					<div class="row">
						<div class="col-12">
							<s:form action="createPost" theme="bootstrap" cssClass="form-create-post" id="form-create-post">
								<div class="col-12">
									<s:textarea cssClass="form-control-sm" id="content" name="content" value=""/>
								</div>
								<div class="col-12">
									<s:submit cssClass="btn btn-sm btn-primary" id="btn-create-post" value="%{getText('btn.submit')}"/>
								</div>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</s:if>

	</div>

</section>