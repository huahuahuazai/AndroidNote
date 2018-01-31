#! usr/bin/python
# coding=utf-8

import hexo

import os

import webbrowser

hexo_url = '/media/jixiaoyong/新加卷/Develop/hexo/blog'

hexo_public_dir = '/media/jixiaoyong/新加卷/Develop/hexo/blog/public'

hexo_post_dir = '/media/jixiaoyong/新加卷/Develop/hexo/blog/source/_posts'

git_dir = '/home/jixiaoyong/Desktop/jixiaoyong.github.io'

git_backup_dir = '/home/jixiaoyong/Desktop/jixiaoyong.github.io/blog/backup/sources/_posts'


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



hexo.generate(hexo_url)

print('post or not ')

is_post = raw_input('y/n:')

if 'n' == is_post:

	print(r'you choose "not",goodbye')

	os._exits(0)

elif 'y' == is_post:

	print('yes')


msg = (r'"update post 《%s》"' % file_name)
	
hexo.post(hexo_public_dir,git_dir,msg)

hexo.backup(hexo_post_dir,git_backup_dir,file_name)