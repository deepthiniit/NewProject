/**
 * 
 */
/**
 * BlogService
 */

app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		console.log(blog.blogContent);
		return $http.post("http://localhost:8030/ProAngMe/addBlog",blog);
	}
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8030/ProAngMe/getblogs/"+0)
	}
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8030/ProAngMe/getblogs/"+1)
	}
	blogService.getBlog=function(id){
		return $http.get("http://localhost:8030/ProAngMe/getblog/"+id);
	}
	blogService.approve=function(blog){
		
		return $http.put("http://localhost:8030/ProAngMe/approve",blog)
	    
	}
   blogService.reject=function(blog,rejectionReason){
	 
    	return $http.put("http://localhost:8030/ProAngMe/reject/"+rejectionReason,blog)
    	
   }
   blogService.updateLikes=function(id){
	   console.log('updating likes in service for blog '+id);
	   return $http.put("http://localhost:8030/ProAngMe/updatelikes/"+id);
   }
   blogService.addComment=function(id,blogComment){         
	   return $http.post("http://localhost:8030/ProAngMe/addcomment/"+id,blogComment);             
   }
   blogService.getBlogComments=function(id){
	   return $http.get("http://localhost:8030/ProAngMe/blogcomments/"+id)
   }
   blogService.hasUserLikedBlog=function(id){
	   return $http.get("http://localhost:8030/ProAngMe/blogcomments/"+id)
   }
	return blogService;
	
})