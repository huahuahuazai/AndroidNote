#! usr/bin/python
# coding=utf-8

import hexo

import os

import webbrowser

import file_tools



hexo_url = '/media/jixiaoyong/新加卷/Develop/hexo/blog'

hexo_public_dir = '/media/jixiaoyong/新加卷/Develop/hexo/blog/public'

hexo_post_dir = '/media/jixiaoyong/新加卷/Develop/hexo/blog/source/_posts'

git_dir = '/home/jixiaoyong/Desktop/jixiaoyong.github.io'

git_backup_dir = '/home/jixiaoyong/Desktop/jixiaoyong.github.io/blog/backup/sources/_posts'

image_dir = '/media/jixiaoyong/新加卷/Develop/hexo/images/'

image_git_dir = '/home/jixiaoyong/Desktop/jixiaoyong.github.io/blog/images/default'

image_url = 'http://jixiaoyong.github.io/blog/images/default'



print('input your file name')

file_name = raw_input('file name is：')

os.chdir(hexo_url)

if not file_name == '':
	
	hexo.new(file_name)

	webbrowser.open('%s/%s.md' % (hexo_post_dir,file_name))



print('are you finish your post?')

is_finish = raw_input('y/n:')

if 'n' == is_finish:

	print('work harder ,bye~')
	
	os._exits(0)


#改变文章中图片的链接为github链接


suddir = file_tools.copy_image(image_dir,image_git_dir)

image_web_url = (r'%s/%s/'%(image_url,suddir))

os.chdir(hexo_post_dir)

file_tools.change_image_url(file_name,image_dir,image_web_url)

raise error


hexo.generate(hexo_url)

print('post or not ')

is_post = raw_input('y/n:')

if 'n' == is_post:

	print(r'you choose "not",goodbye')

	os._exits(0)

elif 'y' == is_post:

	print('yes')


msg = (r'"update post 《%s》"' % file_name)


hexo.backup(hexo_post_dir,git_backup_dir,file_name)
	
hexo.post(hexo_public_dir,git_dir,msg)
