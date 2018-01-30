#! usr/bin/python
# coding=utf-8

import os

def copy_dirs(source_dir, target_dir):
	
	os_name = os.name

	if 'nt' == os_name:
		
		pass
	elif 'posix' == os_name:

		cmd = ('cp -rf %s/. %s' % (source_dir,target_dir))

		os.system(cmd)