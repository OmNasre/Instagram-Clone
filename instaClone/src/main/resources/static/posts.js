// Show modal for adding a new post
document.getElementById("addPostButton").onclick = function() {
	document.getElementById("addPostModal").style.display = "block";
}

// Close modal
document.getElementById("closeModal").onclick = function() {
	document.getElementById("addPostModal").style.display = "none";
}

// Close modal when clicking outside
window.onclick = function(event) {
	if (event.target == document.getElementById("addPostModal")) {
		document.getElementById("addPostModal").style.display = "none";
	}
}

// Handle form submission for adding a new post
document.getElementById("addPostForm").onsubmit = function(event) {
	event.preventDefault();
	const caption = document.getElementById("caption").value;
	const imageUrl = document.getElementById("imageUrl").value;
	console.log('Caption:', caption);
	console.log('Image URL:', imageUrl);

	// Submit the new post to the server
	fetch('/add-post', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ caption: caption, imageUrl: imageUrl })
	})
		.then(response => {
			console.log(response); // Log the full response
			if (response.ok) {
				window.location.reload(); // Reload the page to see the new post
			} else {
				return response.json().then(err => { throw new Error(err.message); });
			}
		})
		.catch(error => console.error('Error:', error));
}

let isLiking = false; // Prevent multiple like actions in quick succession
function likePost(button) {
    if (isLiking) return; // Prevent multiple triggers
    isLiking = true;

    const postId = button.getAttribute("data-post-id");
    const isLiked = button.classList.contains("liked");

    fetch(`/likes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ postId: postId })
    })
    .then(response => {
        if (response.ok) {
            // Toggle liked class based on previous state
            button.classList.toggle('liked', !isLiked);
            window.location.reload(); // Refresh to update like count
        } else {
            return response.json().then(err => { throw new Error(err.message); });
        }
    })
    .catch(error => console.error('Error:', error))
    .finally(() => isLiking = false); // Reset flag after API call
}


// Attach event listeners to like buttons
document.querySelectorAll('.like-button').forEach(button => {
	button.addEventListener('click', function() {
		likePost(this);
	});
});



function editComment(button) {
	const commentId = button.getAttribute("data-comment-id");
	const commentText = button.previousElementSibling.innerText; // Get the current comment text

	// Create a prompt to edit the comment
	const newCommentText = prompt("Edit your comment:", commentText);
	if (newCommentText) {
		// Send the updated comment to the server
		fetch(`/comments/edit?commentId=${commentId}&text=${encodeURIComponent(newCommentText)}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			}
		})
			.then(response => {
				if (response.ok) {
					// Update the UI to reflect the edited comment
					button.previousElementSibling.innerText = newCommentText; // Update the comment text in the UI
				} else {
					alert('You are not authorized to edit this comment.');
				}
			})
			.catch(error => console.error('Error:', error));
	}
}
function showEditCommentModal(button) {
    const commentId = button.getAttribute('data-comment-id');
    const commentText = button.closest('.comment-item').querySelector('span').textContent;

    // Set the comment text and ID in the modal inputs
    document.getElementById('editCommentText').value = commentText;
    document.getElementById('editCommentId').value = commentId;

    // Display the modal
    document.getElementById('editCommentModal').style.display = 'flex'; // Ensure it's flex to center it
}

// Close the modal when clicking the "Close" button
document.getElementById("closeEditModal").onclick = function() {
    document.getElementById("editCommentModal").style.display = "none";
};

// Handle form submission for editing a comment
document.getElementById("editCommentForm").onsubmit = function(event) {
	event.preventDefault();
	const commentId = document.getElementById("editCommentId").value;
	const text = document.getElementById("editCommentText").value;

	fetch(`/comments/edit`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams({ id: commentId, text: text })
	})
		.then(response => {
			if (response.ok) {
				window.location.reload(); // Reload to see updated comment
			} else {
				return response.json().then(err => { throw new Error(err.message); });
			}
		})
		.catch(error => console.error('Error:', error));
}


function deleteComment(button) {
	const commentId = button.getAttribute('data-comment-id');

	fetch(`/comments/delete`, {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams({ id: commentId })
	})
		.then(response => {
			if (response.ok) {
				window.location.reload(); // Reload to see the comment removed
			} else {
				return response.json().then(err => { throw new Error(err.message); });
			}
		})
		.catch(error => console.error('Error:', error));
}

function deletePost(button) {
    const postId = button.getAttribute("data-post-id");

    if (confirm("Are you sure you want to delete this post?")) {
        fetch(`/posts/${postId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                // Remove the post from the DOM
                button.closest('.post-card').remove();
            } else {
                alert("Failed to delete post.");
            }
        })
        .catch(error => {
            console.error("Error deleting post:", error);
        });
    }
}
