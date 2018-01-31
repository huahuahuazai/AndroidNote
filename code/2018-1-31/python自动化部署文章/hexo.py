#! sur/bin/python
# coding=utf-8

import os

import webbrowser

import shutil

import file_tools

def new(title):

	cmd = ('hexo new %s' % title)

	os.system(cmd)


def generate(hexo_dir):

	os.chdir(hexo_dir)

	os.system('hexo clean')
	
	os.system('hexo generate')

	os.system('hexo server')

	webbrowser.open('http://localhost:4000/blog')


def post(source_dir, target_dir, msg):
	
	try:

		os.chdir(target_dir)

		os.system('git pull')

		blog_dir = ('%s/blog' % target_dir)

		file_tools.copy_dirs(source_dir,blog_dir)
		
		# shutil.copy(source_dir,blog_dir)

		print('copy %s to %s end' % (source_dir, blog_dir))

		os.chdir(target_dir)

		status = os.popen('git status')

		if 'nothing to commit' in status:
			
			print('nothing to commit')

			os._exit()

		os.system('git add -A')

		os.system('git commit -m %s' % msg)

		os.system('git push')

		print('waiting for browser ...')

		webbrowser.open('http://jixiaoyong.github.io/blog/')

		# os.system(cmd)

	except Exception as e:

		raise e


def backup(source_dir,target_dir,file_name):
	
	try:
		
		source_file = ('%s/%s.md' % (source_dir, file_name))

		target_file = ('%s/%s.md' % (target_dir, file_name))

		#需要增加汉字处理

		shutil.copyfile(source_file,target_file)

		os.chdir(target_dir)

		dirs = os.listdir(target_dir)

		if ('%s.md'%file_name) in dirs:
			
			print('backup %s succeed' % file_name)

			return 'true'

		else:

			print('backup %s failed' % file_name)

			return 'false'

	except Exception as e:

		raise e
