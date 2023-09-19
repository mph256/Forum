$(document).ready(() => {

	function loadShowPasswordListener() {

		$(".btn-show-password").on("click", function() {

			const inputPassword = $("#static-password");

			inputPassword.prop("type", "text");

			setTimeout(() => {

				inputPassword.prop("type", "password");

			}, 3000);

		});

	}

	function loadSearchThreadListener() {

		$(".form-search-thread").on("submit", function(e) {

			e.preventDefault();

			if("" == $("#tags").val())
				$(".btn-refresh-threads").trigger("click");
			else {

				const data = $(this).serialize();

				$.ajax({
					url: "searchThread?" + data,
					type: "post",
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					success: function(result) {

						const numberOfItems = result.threads.length;
						const numberOfItemsToShowPerPage = parseInt($("#threads-page-length").val());
						const currentPage = 1;

						$("#threads-length").val(numberOfItems);
						$("#page-current").val(currentPage);

						const table = refreshThreads(result.threads, numberOfItemsToShowPerPage, currentPage);
						const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

						$("#threads").replaceWith(table);
						$("#pagination-threads").replaceWith(`<div class='pagination-threads' id='pagination-threads'>\${pagination}</div>`);

						$(".tr-thread").unbind();
						$(".pagination-threads .page-link").unbind();

						loadThreadListener();
						loadThreadsPaginationListeners();

					}
				});

			}

		});

	}

	function loadThreadListener() {

		$(".tr-thread").on("click", function() {

			const buttonId = $(this).find("form").attr("id").split("-")[3];

			$(".tr-thread").unbind();

			$(`#btn-redirect-thread-\${buttonId}`).trigger("click");

			loadThreadListener();

		});

	}

	function loadRefreshThreadsListener() {

		$(".btn-refresh-threads").on("click", function() {

			$("#tags").val("");

			$.ajax({
				url: "refreshThreads",
				type: "get",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItems = result.threads.length;
					const numberOfItemsToShowPerPage = parseInt($("#threads-page-length").val());
					const currentPage = 1;

					$("#threads-length").val(numberOfItems);
					$("#page-current").val(currentPage);

					const table = refreshThreads(result.threads, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#threads").replaceWith(table);
					$("#pagination-threads").replaceWith(`<div class='pagination-threads' id='pagination-threads'>\${pagination}</div>`);

					$(".tr-thread").unbind();
					$(".pagination-threads .page-link").unbind();

					loadThreadListener();
					loadThreadsPaginationListeners();

				}
			});

		});

	}

	function refreshThreads(threads, numberOfItemsToShowPerPage, currentPage) {

		let html = `<table class='table' id='threads'>\
			<thead>\
			<tr>\
			<th scope='col'><s:text name='table.threads.subject'/></th>\
			<th scope='col'><s:text name='table.threads.author'/></th>\
			<th scope='col'><s:text name='table.threads.posts.counter'/></th>\
			<th scope='col'><s:text name='table.threads.posts.last'/></th>\
			</tr>\
			</thead>\
			<tbody>`;

		if(threads.length > 0) {

			let i = 1;

			for(let thread of threads) {

				if(((i - 1) >= (currentPage - 1) * numberOfItemsToShowPerPage) && ((i - 1) <= ((currentPage - 1) * numberOfItemsToShowPerPage) + numberOfItemsToShowPerPage - 1))
					html += refreshThread(thread);

				i++;

			}

		} else
			html += `<tr class='text-center'>\
			<td colspan='4'><s:text name='table.empty'/></td>\
			</tr>`;

		html += `</tbody>\
			<tfoot>\
			<tr>\
			<th scope='col'><s:text name='table.threads.subject'/></th>\
			<th scope='col'><s:text name='table.threads.author'/></th>\
			<th scope='col'><s:text name='table.threads.posts.counter'/></th>\
			<th scope='col'><s:text name='table.threads.posts.last'/></th>\
			</tr>\
			</tfoot>\
			</table>`;

		return html;

	}

	function refreshThread(thread) {

		let html = `<tr class='tr-thread'>\
			<td>\
			<s:form action='thread' method='post' theme='simple' cssClass='form-redirect-thread' id='form-redirect-thread-\${thread.id}'>\
			<s:hidden id='thread-\${thread.id}-id' name='id' value='\${thread.id}'/>\
			<s:submit cssClass='btn btn-sm btn-link btn-redirect-thread link-dark p-0' id='btn-redirect-thread-\${thread.id}' value='\${thread.title}' method='loadPage'/>\
			</s:form>\
			</td>\
			<td><i class='bi bi-person'></i>&nbsp;&nbsp;\${thread.user.login}</td>\
			<td><i class='bi bi-chat-dots'></i>&nbsp;&nbsp;\${thread.posts.length}</td>\
			<td><i class='bi bi-calendar4'></i>&nbsp;&nbsp;\${$.date(thread.lastUpdate)}</td>\
			</tr>`;

		return html;

	}

	function loadThreadsPaginationListeners() {

		$(".pagination-threads .page-link").on("click", function(e) {

			$.ajax({
				url: "refreshThreads",
				type: "get",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItemsToShowPerPage = parseInt($("#threads-page-length").val());

					const inputSearchThreadByTags = $("#tags").val();

					let threads = [];

					if("" !== inputSearchThreadByTags) {

						const tags = inputSearchThreadByTags.split(" ");

						for(let thread of result.threads) {

							if(thread.tags.filter(x => tags.includes(x.label)).length !== 0)
								threads.push(thread);

						}

					} else
						threads = result.threads;

					const numberOfItems = threads.length;
					const lastPage = Math.ceil(numberOfItems/numberOfItemsToShowPerPage);
					const targetPage = ("\u00ab" === e.target.innerText)?1:(("\u00bb" === e.target.innerText)?lastPage:parseInt(e.target.innerText));

					let currentPage;

					if(targetPage > lastPage)
						currentPage = lastPage;
					else
						currentPage = targetPage;

					$("#threads-length").val(numberOfItems);
					$("#page-current").val(currentPage);			

					const table = refreshThreads(threads, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#threads").replaceWith(table);
					$("#pagination-threads").replaceWith(`<div class='pagination-threads' id='pagination-threads'>\${pagination}</div>`);

					$(".tr-thread").unbind();
					$(".pagination-threads .page-link").unbind();

					loadThreadListener();
					loadThreadsPaginationListeners();

				}
			});
	
		});

	}

	function loadPostsPaginationListeners() {

		$(".pagination-posts .page-link").on("click", function(e) {

			const threadId = $("#thread-id").val();

			$.ajax({
				url: "refreshPosts?id=" + threadId,
				type: "get",
				contentType:"application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItems = result.thread.posts.length;
					const numberOfItemsToShowPerPage = parseInt($("#posts-page-length").val());			
					const lastPage = Math.ceil(numberOfItems/numberOfItemsToShowPerPage);
					const targetPage = ("\u00ab" === e.target.innerText)?1:(("\u00bb" === e.target.innerText)?lastPage:parseInt(e.target.innerText));

					let currentPage;

					if(targetPage > lastPage)
						currentPage = lastPage;
					else
						currentPage = targetPage;

					$("#posts-length").val(numberOfItems);
					$("#page-current").val(currentPage);

					const posts = refreshPosts(result.thread.posts, result.user, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#posts").replaceWith(posts);
					$("#pagination-posts").replaceWith(`<div class='pagination-posts' id='pagination-posts'>\${pagination}</div>`);

					$(".btn-show-form-edit-post").unbind();
					$(".form-delete-post").unbind();

					$(".pagination-posts .page-link").unbind();

					loadEditPostListener();
					loadDeletePostListener();

					loadPostsPaginationListeners();		

				}
			});

		});

	}

	function refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage) {

		if(numberOfItems > numberOfItemsToShowPerPage) {

			let html = `<nav aria-label='Pagination'>\
				<ul class='pagination pagination-sm justify-content-end'>`;

			if(currentPage != 1)
				html += `<li class='page-item'>\
					<a class='page-link' href='#' aria-label='Previous'>\
					<span aria-hidden='true'>&laquo;</span>\
					</a>\
					</li>`;
			else
				html += `<li class='page-item disabled'>\
					<a class='page-link' href='#' aria-label='Previous' aria-disabled='true'>\
					<span aria-hidden='true'>&laquo;</span>\
					</a>\
					</li>`;

			for(i = 1; i <= Math.ceil(numberOfItems/numberOfItemsToShowPerPage); i++) {

				if(currentPage == i)
					html += `<li class='page-item active' aria-current='page'>\
						<a class='page-link' href='#'>\${i}</a>\
						</li>`;
				else
					html += `<li class='page-item'>\
						<a class='page-link' href='#'>\${i}</a>\
						</li>`;

			}

			if(currentPage != Math.ceil(numberOfItems/numberOfItemsToShowPerPage))
				html += `<li class='page-item'>\
					<a class='page-link' href='#' aria-label='Next'>\
					<span aria-hidden='true'>&raquo;</span>\
					</a>\
					</li>`;
			else
				html += `<li class='page-item disabled'>\
					<a class='page-link' href='#' aria-label='Next' aria-disabled='true'>\
				    <span aria-hidden='true'>&raquo;</span>\
					</a>\
					</li>`;

				html += `</ul>\
					</nav>`;

			return html;

		}

		return ``;

	}

	function loadRefreshPostsListener() {

		$(".btn-refresh-posts").on("click", function() {

			const threadId = $("#thread-id").val();

			$.ajax({
				url: "refreshPosts?id=" + threadId,
				type: "get",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItems = result.thread.posts.length;
					const numberOfItemsToShowPerPage = parseInt($("#posts-page-length").val());
					const currentPage = 1;

					$("#posts-length").val(numberOfItems);
					$("#page-current").val(currentPage);

					const posts = refreshPosts(result.thread.posts, result.user, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#posts").replaceWith(posts);
					$("#pagination-posts").replaceWith(`<div class='pagination-posts' id='pagination-posts'>\${pagination}</div>`);

					$(".btn-show-form-edit-post").unbind();
					$(".form-delete-post").unbind();

					$(".pagination-posts .page-link").unbind();

					loadEditPostListener();
					loadDeletePostListener();

					loadPostsPaginationListeners();		

				}
			});

		});

	}

	function refreshPosts(posts, user, numberOfItemsToShowPerPage, currentPage) {

		let html = `<div id='posts' class='mt-3'>`;

		if(posts.length > 0) {

			let i = 1;

			for(let post of posts) {

				if(((i - 1) >= (currentPage - 1) * numberOfItemsToShowPerPage) && ((i - 1) <= ((currentPage - 1) * numberOfItemsToShowPerPage) + numberOfItemsToShowPerPage - 1))
					html += refreshPost(post, user);

				i++;

			}

		}

		html += `</div>`;

		return html;

	}

	function refreshPost(post, user) {

		let html = `<div id='post-\${post.id}'>\
			<div class='card mb-3'>\
			<div class='card-header'>\
			<div class='row'>\
			<div class='col-1'>\
			<img src='/Forum/assets/img/users/\${post.user.profilePicture.name}' alt="<s:text name='img.alt.user.profile.2'/>\${post.user.login}" class='rounded-circle'>\
			</div>\
			<div class='col-11'>\
			<div class='row'>\
			<div class='col-8 col-md-9 col-lg-10 ps-0'>\
			<span class='fw-semibold post-user-login'>\${post.user.login}</span>\
			</div>\
			<div class='col-4 col-md-3 col-lg-2'>`;

		if(user !== null && (post.user.login === user.login)) {

			html += `<div class='row'>\
				<div class='offset-6 col-3 offset-xl-8 col-xl-2'>\
				<button type='button' class='btn btn-sm btn-show-form-edit-post p-0' id='btn-show-form-edit-post-\${post.id}' name='btn-show-form-edit-post'>\					
				<i class='bi bi-pencil-square'></i>\
				</button>\
				</div>\
				<div class='col-3 col-xl-2'>\		
				<s:form action='deletePost' method='post' theme='simple' cssClass='form-delete-post' id='form-delete-post-\${post.id}'>\
				<s:hidden id='post-\${post.id}-id' name='id' value='\${post.id}'/>\
				<s:submit type='button' cssClass='btn btn-sm btn-delete-post p-0' id='btn-delete-post-\${post.id}' value=''>\
				<i class='bi bi-x-lg'></i>\
				</s:submit>\
				</s:form>\		
				</div>\
				</div>`;

		}

		html += `</div>\
			</div>\
			<div class='row'>`;

		if(post.publicationDate !== post.lastUpdate)
			html += `<div class='col-6 col-md-5 col-lg-3 col-xxl-2 ps-0'>\
				<span class='post-date-publication'>\${$.date(post.publicationDate)}</span>\
				</div>\
				<div class='col-6 col-md-7 col-lg-9 col-xxl-10 ps-0'>\
				<span class='fw-light post-date-update'>\${$.date(post.lastUpdate)}</span>\
				</div>`;
		else
			html += `<div class='col-12 ps-0'>\
				<span class='post-date-publication'>\${$.date(post.publicationDate)}</span>\
				</div>`;

		html += `</div>\
			</div>\
			</div>\
			</div>\
			<div class='card-body'>\
			<div id='post-\${post.id}-content'>\
			<p class='card-text'>\${post.content}</p>\
			</div>\
			</div>\
			</div>\
			</div>`;

		return html;

	}

	function loadCreatePostListener() {

		$(".form-create-post").on("submit", function(e) {

			e.preventDefault();

			const threadId = $("#thread-id").val();

			const data = $(this).serialize();

			$.ajax({
				url: "createPost?id=" + threadId + "&" + data,
				type: "post",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItems = result.thread.posts.length;
					const numberOfItemsToShowPerPage = parseInt($("#posts-page-length").val());			
					const lastPage = Math.ceil(numberOfItems/numberOfItemsToShowPerPage);
					const currentPage = lastPage;

					$("#posts-length").val(numberOfItems);
					$("#page-current").val(currentPage);

					const posts = refreshPosts(result.thread.posts, result.user, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#posts").replaceWith(posts);
					$("#pagination-posts").replaceWith(`<div class='pagination-posts' id='pagination-posts'>\${pagination}</div>`);

					$(".btn-show-form-edit-post").unbind();
					$(".form-delete-post").unbind();

					$(".pagination-posts .page-link").unbind();

					loadEditPostListener();
					loadDeletePostListener();

					loadPostsPaginationListeners();

					$("#content").val("");

				}
			});

		});

	}

	function loadEditPostListener() {

		$(".btn-show-form-edit-post").on("click", function() {

			const postId = this.id.split("-")[5];
			const content = $(`#post-\${postId}-content p`).text().trim();

			let form = `<form action='/Forum/editPost' method='post' class='form-edit-post' id='form-edit-post-\${postId}'>\
				<div class='row'>\
				<div class='col-12'>\
				<input type='hidden' id='post-\${postId}-id' name='id' value='\${postId}'>\
				<textarea class='form-control' id='post-\${postId}-content' name='content'>\${content}</textarea>\
				</div>\
				</div>\
				<div class='row mt-2'>\
				<div class='col-12'>\
				<button class='btn btn-sm btn-primary btn-edit-post' id='btn-edit-post-\${postId}' name='btn-edit-post'><s:text name='btn.submit'/></button>\
				<button type='button' class='btn btn-sm btn-secondary btn-hide-form-edit-post' id='btn-hide-form-edit-post-\${postId}' name='btn-hide-form-edit-post'><s:text name='btn.cancel'/></button>\
				</div>\
				</div>\
				</form>`;

			$(`#post-\${postId}-content`).replaceWith(`<div id='post-\${postId}-content'>\${form}</div>`);

			$(".btn-hide-form-edit-post").on("click", function() {
				$(`#post-\${postId}-content`).replaceWith(`<div id='post-\${postId}-content'><p class='card-text'>\${content}</p></div>`);
			});

			$(".form-edit-post").on("submit", function (e) {

				e.preventDefault();

				const data = $(this).serialize();

				$.ajax({
					url: "editPost?" + data,
					type: "post",
					contentType: "application/json; charset=utf-8",
					datatype: "json",
					success: function(result) {

						const post = refreshPost(result.post, result.user);

						$(`#post-\${result.post.id}`).replaceWith(post);

						$(".btn-show-form-edit-post").unbind();
						$(".form-delete-post").unbind();

						loadEditPostListener();
						loadDeletePostListener();

					}
				});

			});

		});

	}

	function loadDeletePostListener() {

		$(".form-delete-post").on("submit", function(e) {

			e.preventDefault();

			const data = $(this).serialize();

			$.ajax({
				url: "deletePost?" + data,
				type: "post",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(result) {

					const numberOfItems = result.thread.posts.length;
					const numberOfItemsToShowPerPage = parseInt($("#posts-page-length").val());
					const targetPage = parseInt($("#page-current").val());
					const lastPage = Math.ceil(numberOfItems/numberOfItemsToShowPerPage);

					let currentPage;

					if(targetPage > lastPage)
						currentPage = lastPage;
					else
						currentPage = targetPage;

					$("#posts-length").val(numberOfItems);
					$("#page-current").val(currentPage);

					const posts = refreshPosts(result.thread.posts, result.user, numberOfItemsToShowPerPage, currentPage);
					const pagination = refreshPagination(numberOfItems, numberOfItemsToShowPerPage, currentPage);

					$("#posts").replaceWith(posts);
					$("#pagination-posts").replaceWith(`<div class='pagination-posts' id='pagination-posts'>\${pagination}</div>`);

					$(".btn-show-form-edit-post").unbind();
					$(".form-delete-post").unbind();

					$(".pagination-posts .page-link").unbind();

					loadEditPostListener();
					loadDeletePostListener();

					loadPostsPaginationListeners();

				}
			});

		});

	}

	function loadDeleteAccountListener() {

		let confirmation = false;

		$(".form-delete-account").on("submit", function(e) {

			if(!confirmation) {

				e.preventDefault();

				$(".btn-delete-account-confirmation").on("click", function() {

					 confirmation = true;

					 $(".btn-delete-account").trigger("click");

				});

			}

		});

	}

	$.date = (param) => {

		let date = new Date(param);

		let day = date.getDate();
		let month = date.getMonth() + 1;
		let year = date.getFullYear();
		let hours = date.getHours();
		let minutes = date.getMinutes();
		let secondes = date.getSeconds();

		if(day < 10)
			day = `0\${day}`;

		if(month < 10)
			month = `0\${month}`;

		if(hours < 10)
			hours = `0\${hours}`;

		if(minutes < 10)
			minutes = `0\${minutes}`;

		if(secondes < 10)
			secondes = `0\${secondes}`;

		return `\${day}/\${month}/\${year} \${hours}:\${minutes}:\${secondes}`;

	};

	let page = location.href.substring(location.href.lastIndexOf("/") + 1);

	if(page.indexOf("home") !== -1) {

		loadSearchThreadListener();

		loadThreadListener();

		loadRefreshThreadsListener();

		loadThreadsPaginationListeners();

	}

	if(page.indexOf("thread") !== -1) {

		loadRefreshPostsListener();

		loadPostsPaginationListeners();

		loadCreatePostListener();
		loadEditPostListener();
		loadDeletePostListener();

	}

	if(page.indexOf("profile") !== -1) {

		loadShowPasswordListener();

		loadDeleteAccountListener();

	}

});