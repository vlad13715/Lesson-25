package com.example.lesson25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lesson25.PostsClient.getPostsService
import com.example.lesson25.databinding.FragmentFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() {

    private var binding:FragmentFirstBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFirstBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val postService= getPostsService()

        val posts=CoroutineScope(Dispatchers.IO).async{
            postService.getPicture()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val mainPosts = posts.await()

            withContext(Dispatchers.Main){
                Glide.with(requireContext()).load(mainPosts.photos[0].url).into(binding!!.ivPostPicture)
                binding?.ivPostTitle?.text= mainPosts.photos[0].title
                binding?.ivPostDescription?.text=mainPosts.photos[0].description
            }


        }
    }
}