import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class SwitchButton extends StatefulWidget {
  HomePage createState() => HomePage();
}

class HomePage extends State<SwitchButton> {

  bool _lights = false;

  @override
  Widget build(BuildContext context) {
    Widget button = new MergeSemantics(
      child: new ListTile(
        title: new Text('Lights is '+(_lights?'on':'off')),
        trailing: new CupertinoSwitch(
          value: _lights,
          activeColor: Colors.green,
          onChanged: (bool value) {
            setState(() {
              _lights = value;
            });
          },
        ),
        onTap: () {
          setState(() {
            _lights = !_lights;
          });
        },
      ),
    );

    return button;
  }
}