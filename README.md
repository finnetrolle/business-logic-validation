# business-logic-validation
[![GitHub version](https://badge.fury.io/gh/finnetrolle%2Fbusiness-logic-validation.svg)](https://badge.fury.io/gh/finnetrolle%2Fbusiness-logic-validation)
[![Build Status](https://travis-ci.org/finnetrolle/business-logic-validation.svg?branch=master)](https://travis-ci.org/finnetrolle/business-logic-validation)
[![codecov](https://codecov.io/gh/finnetrolle/business-logic-validation/branch/master/graph/badge.svg)](https://codecov.io/gh/finnetrolle/business-logic-validation)

Show more info about blv at http://www.finnetrolle.ru/business-logic-validation/

This library can help you organize code for validation process.

## Installation
Just add to your pom.xml dependency
```
<dependency>
    <groupId>ru.finnetrolle</groupId>
    <artifactId>business-logic-validation</artifactId>
    <version>0.4.0</version>
</dependency>
```

## Usage

You can see examples in test folder

### Simple example

This is a simpliest validation example:

```
    ValidationResult result = ValidationResult.validate("James")
            .with("length > 3", name -> name.length() > 3);
```
We're trying to validate that name "James" has length > 3

### Group validation

You can validate multiple times to get one big result. For example you have two lists - passport ids and names.
You want one result with groups such 'passport validation' and 'name validation'. Let's make it:

```
VALIDATE
    passports BY GROUP 'passport validation' CONTAINS(
        length = 10,
        contains digit 6)
AND names BY GROUP 'name validation' CONTAINS(
        length > 2)
```

Simple? I think yes. Let's take a look at code

```
        ValidationResult result = ValidationResult
                .validate(PASSPORTS).by(RuleGroup.named("Passport Validation").validating(
                        SimpleRule.error("length = 10", p -> p.length() == 10),
                        SimpleRule.error("contains digit 6", p -> p.contains("6"))))
                .and(NAMES).by(RuleGroup.named("Name Validation").validating(
                        SimpleRule.error("Name length > 2", p -> p.length() > 2)))
```
