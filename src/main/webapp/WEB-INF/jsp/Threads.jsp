<section class="section-threads" id="section-threads">

	<s:set var="numberOfThreads" value="threads.size"/>
	<s:set var="numberOfThreadsToShowPerPage" value="5"/>
	<s:set var="currentPage" value="1"/>

	<input type="hidden" id="threads-length" name="threads-length" value="<s:property value="#numberOfThreads"/>">
	<input type="hidden" id="threads-page-length" name="threads-page-length" value="<s:property value="#numberOfThreadsToShowPerPage"/>">
	<input type="hidden" id="page-current" name="page-current" value="<s:property value="#currentPage"/>">

	<div class="container">

		<div class="row">
			<div class="offset-3 col-6">
				<s:form action ="searchThread" method="post" theme="bootstrap" cssClass="form-search-thread">
					<div class="row g-2 align-items-center">
						<div class="offset-2 col-8">
							<s:textfield cssClass="form-control-sm" id="tags" name="tags" placeholder="%{getText('label.search')}"/>
						</div>
						<div class="col-2">
							<s:submit type="button" cssClass="btn btn-sm btn-primary btn-search-thread" id="btn-search-thread" value="">
								<i class="bi bi-search"></i>
							</s:submit>
						</div>
					</div>
				</s:form>
			</div>
		</div>
		
		<div class="row mt-4">
			<div class="offset-11 col-1">
				<div class="d-flex justify-content-end">
					<button type="button" class="btn btn-sm btn-primary h-100 btn-refresh-threads" id="btn-refresh-threads" name="btn-refresh-threads">
						<i class="bi bi-arrow-clockwise"></i>
					</button>
				</div>
			</div>
		</div>

		<table class="table" id="threads">
			<thead>
				<tr>
					<th scope="col"><s:text name="table.threads.subject"/></th>
					<th scope="col"><s:text name="table.threads.author"/></th>
					<th scope="col"><s:text name="table.threads.posts.counter"/></th>
					<th scope="col"><s:text name="table.threads.posts.last"/></th>
				</tr>
			</thead>
			<tbody>
				<s:if test="#numberOfThreads > 0">
					<s:iterator value="threads" var="thread" status="status">
						<s:if test="(#status.index >= (#currentPage - 1) * #numberOfThreadsToShowPerPage)
							&& (#status.index <= ((#currentPage - 1) * #numberOfThreadsToShowPerPage) + #numberOfThreadsToShowPerPage - 1)">
							<tr class="tr-thread">
								<td>
									<s:form action="thread" method="post" theme="simple" cssClass="form-redirect-thread" id="%{'form-redirect-thread-' + #thread.id}">
										<s:hidden id="%{'thread-' + #thread.id + '-id'}" name="id" value="%{#thread.id}"/>
										<s:submit cssClass="btn btn-sm btn-link btn-redirect-thread link-dark p-0" id="%{'btn-redirect-thread-' + #thread.id}" value="%{#thread.title}" method="loadPage"/>
									</s:form>
								</td>
								<td>
									<i class="bi bi-person"></i>&nbsp;
									<s:property value="%{#thread.user.login}"/>
								</td>
								<td>
									<i class="bi bi-chat-dots"></i>&nbsp;
									<s:property value="%{#thread.posts.size}"/>
								</td>
								<td>
									<i class="bi bi-calendar4"></i>&nbsp;
									<s:text name="format.datetime">
										<s:param value="%{#thread.lastUpdate}"/>
									</s:text>
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</s:if>
				<s:else>
					<tr class='text-center'>
						<td colspan='4'><s:text name='table.empty'/></td>
					</tr>
				</s:else>
			</tbody>
			<tfoot>
				<tr>
					<th scope="col"><s:text name="table.threads.subject"/></th>
					<th scope="col"><s:text name="table.threads.author"/></th>
					<th scope="col"><s:text name="table.threads.posts.counter"/></th>
					<th scope="col"><s:text name="table.threads.posts.last"/></th>
				</tr>
			</tfoot>
		</table>

		<div class="pagination-threads" id="pagination-threads">
			<s:if test="#numberOfThreads > #numberOfThreadsToShowPerPage">
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
							end="%{(#numberOfThreads % #numberOfThreadsToShowPerPage == 0)?(#numberOfThreads / #numberOfThreadsToShowPerPage):(#numberOfThreads / #numberOfThreadsToShowPerPage + 1)}">
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
			  			<s:if test="#currentPage != ((#numberOfThreads % #numberOfThreadsToShowPerPage == 0)?(#numberOfThreads / #numberOfThreadsToShowPerPage):(#numberOfThreads / #numberOfThreadsToShowPerPage + 1))">
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
					<h5 class="h5"><s:text name="title.form.create.thread"/></h5>
					<s:form action="thread" method="post" theme="bootstrap" cssClass="form-create-thread" id="form-create-thread">
						<div class="row">
							<div class="col-12">
								<s:textfield cssClass="form-control-sm" id="title" name="title" placeholder="%{getText('label.thread.title')}"/>
								<s:fielderror fieldName="title" cssClass="mt-2 mb-0"/>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<s:textfield cssClass="form-control-sm" id="tags" name="tags" placeholder="%{getText('label.thread.tags')}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<s:textarea cssClass="form-control-sm" id="content" name="content" value=""/>
								<s:fielderror fieldName="content" cssClass="mt-2 mb-0"/>
							</div>
						</div>
						<div class="col-12">
							<s:submit cssClass="btn btn-sm btn-primary" id="btn-create-thread" value="%{getText('btn.submit')}" method="createThread"/>
						</div>
					</s:form>
				</div>
			</div>
		</s:if>

	</div>

</section>