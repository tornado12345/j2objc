// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

//
//  JreRetainedLocalValue.m
//  JreEmulation
//

#ifndef JRERETAINEDLOCALVALUE_H
#define JRERETAINEDLOCALVALUE_H

#import "J2ObjC_common.h"

#if __has_feature(objc_arc)
#error "JreRetainedLocalValue cannot be built with ARC"
#endif

id JreRetainedLocalValue(id value) {
  return [[value retain] autorelease];
}

#endif // JRERETAINEDLOCALVALUE_H
