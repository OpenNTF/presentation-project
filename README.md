# The Presentation Project

This repository collects all parts of the application which is going to be the centre of the presentation project of OpenNTF.

## The idea

The main goal of this idea is to collect presentations from the past decades that were used in

- conference sessions
- webinars
- webcasts
- tutorials
- etc.

We are aware of the various places where people put their presentations, demos and videos but we want to collect them all in a single place. It's a curated aggregator for presentations, like Collaboration Today is an aggregator for news and blog posts.

## Submission App

We started with a simple UI to submit either files or links to presentations for all who want to share something along with some meta data. The app was built with HCL Domino Volt 1.0.5. Since a Volt app is not very usable in a source control system, we put it here as an exported app (a .volt file). If you want to deploy it on your system, you need at least version 1.0.5.

The Volt app is far from being complete but the overall [data model](datamodel.md) is settled more or less. [You can test it live here](https://notesx.net/volt-apps/landing/org/app/9f56efc0-9789-4143-8475-fc39dd0b92bd).

## The goal

As Volt stores data in an NSF, our goal is to produce a frontend for the data. This frontend app is going to be accessible over the OpenNTF website. We are not narrowing down the technology that should be used to create this app since we do not plan to deeply integrate it in the current website - which is based on XPages. This, however, doesn't mean that the app should not be made with XPages - we are open to whatever will fit. The technology stack should only align with the possibilities of the OpenNTF infrastructure. 

Potential options are

- XPages
- AppDev pack, Node.js
- any UI framework for Javascript
- Java app server
- you name it

Of course all options should make it possible to access NSF data. Rule of thumb: keep it simple.

### Features wanted

Some features are mandatory:

- the app should be like a catalog
- search / filter function
- preview images of presenations
- abstract texts for the items

## We want you!

Of course we want you to participate in the process of building this new app for OpenNTF. The project is open-source and it follows the idea of bringing people and creativity together. Any help is appreciated. Even if you are not a developer you can help with testing or writing documentation - yes, there will be a technical and a user documentation ;-)

Possible areas for your support are

- being part of the development for the UI app
- mockups, UI/UX
- developing and improving the submission app (or create something completely different if Volt is not your favorite)
- testing
- writing documentation
- support
- project management

## Side effects

Beside from being part of something bigger you can also learn some new techniques and meet other people. This is not a contest, so we are not going to have 20 different apps that do the same more or less. In the first wave we collect ideas and discuss technologies being used. When this is done, we focus on the coding part itself.

## Join the discussion

We have a dedicated channel in the OpenNTF Slack: https://app.slack.com/client/T0A6JU4PL/C037SDWA073

This page is updated regularly.

|Version|History|
|---|---|
|rev 0.0.1, 2022-03-22|Initial readme|
